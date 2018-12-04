package br.com.blz.testjava.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public void illegalArgumentExceptionHandler() {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NullPointerException.class, EmptyResultDataAccessException.class})
    public void nullPointerExceptionHandler() {
    }
}
