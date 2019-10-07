package br.com.blz.testjava.custom.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ApiExceptionHandler {


    @Order(Ordered.HIGHEST_PRECEDENCE)
    @RestControllerAdvice
    public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

//        @Autowired
//        MsgConfiguration messages;

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
