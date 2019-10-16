package br.com.blz.testjava.interceptor;

import br.com.blz.testjava.exception.CommonAppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> genericHandle(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "There was an internal error, please try again later.";
        logger.fatal("An exception occurred.", ex);
        return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {CommonAppException.class})
    protected ResponseEntity<Object> customHandle(CommonAppException ex, WebRequest request) {
        logger.fatal("An exception occurred.", ex);
        return handleExceptionInternal(ex, ex.getMessage(),
            new HttpHeaders(), ex.getStatus(), request);
    }

}
