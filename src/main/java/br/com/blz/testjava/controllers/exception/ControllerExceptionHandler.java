package br.com.blz.testjava.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> illegalArgumentException(IllegalArgumentException e, HttpServletRequest req){
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
