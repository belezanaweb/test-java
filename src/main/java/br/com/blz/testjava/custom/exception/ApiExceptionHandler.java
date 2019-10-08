package br.com.blz.testjava.custom.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class ApiExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @RestControllerAdvice
    public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {

            List<String> errors = new ArrayList<>();

            for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
                String fails = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                errors.add(fails);
            }

            return buildResponseEntity(new ApiError(status, ex, errors.toString()));
        }

        @ExceptionHandler(ProductNotFoundException.class)
        protected ResponseEntity<Object> handleExceptionNotFound(ProductNotFoundException ex, WebRequest request) {
            return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex, ex.getMessage()));
        }

        @ResponseStatus(HttpStatus.CONFLICT)
        @ExceptionHandler(ProductConflictException.class)
        protected ResponseEntity<Object> handleExceptionConflict(ProductNotFoundException ex, WebRequest request) {
            return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, ex, ex.getMessage()));
        }

        private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
            HttpStatus status = HttpStatus.valueOf(apiError.getStatus());

            return new ResponseEntity<>(apiError, status);
        }
    }
}
