package br.com.blz.testjava.api.exceptionhandler;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {

    @Autowired
    private ApiExceptionHandler apiExceptionHandler;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerInternalServerError(Exception exception, Locale locale) {
        log.error("Error not expected", exception);
        final String errorCode = "error-1";
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorResponse errorResponse = ErrorResponse.of(status, apiExceptionHandler.toApiError(errorCode, locale));
        return ResponseEntity.status(status).body(errorResponse);
    }
}
