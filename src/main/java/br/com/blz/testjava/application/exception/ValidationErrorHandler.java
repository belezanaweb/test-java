package br.com.blz.testjava.application.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ValidationErrorHandler {

    private static final Logger log = LoggerFactory
        .getLogger(ValidationErrorHandler.class);


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorOutputDto handleValidationError(IllegalArgumentException exception) {

        return new ErrorOutputDto(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchFieldException.class)
    public ErrorOutputDto handleValidationError(NoSuchFieldException exception) {
        return new ErrorOutputDto(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler( ResponseStatusException.class)
    public ErrorOutputDto handleValidationError(ResponseStatusException exception) {
        return new ErrorOutputDto(exception.getMessage());
    }


}
