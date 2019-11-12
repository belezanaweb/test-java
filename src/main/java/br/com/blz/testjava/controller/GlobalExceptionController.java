package br.com.blz.testjava.controller;

import br.com.blz.testjava.contract.response.ErrorMessageResponse;
import br.com.blz.testjava.exception.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessageResponse> handleConflictsException(Exception e) {
        ErrorMessageResponse errorMessage = new ErrorMessageResponse(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }
}
