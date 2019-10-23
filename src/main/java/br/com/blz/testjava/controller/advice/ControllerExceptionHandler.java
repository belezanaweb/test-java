package br.com.blz.testjava.controller.advice;

import br.com.blz.testjava.exception.ResourceAlreadyExistsException;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({
        ResourceNotFoundException.class
    })
    public Error handle(final ResourceNotFoundException e) {
        return this.getError(e);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
        ResourceAlreadyExistsException.class
    })
    public Error handleResourceAlreadyExistsException(final ResourceAlreadyExistsException e) {
        return this.getError(e);
    }

    private Error getError(final Exception exception) {
        if (StringUtils.isEmpty(exception.getMessage())) {
            return null;
        }

        return new Error(exception.getMessage());
    }
}
