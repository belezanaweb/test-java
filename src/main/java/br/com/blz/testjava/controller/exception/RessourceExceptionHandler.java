package br.com.blz.testjava.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.blz.testjava.model.exception.ExistingProductException;
import br.com.blz.testjava.model.exception.ObjectNotFoundException;

@ControllerAdvice
public class RessourceExceptionHandler {
	
	@ExceptionHandler(ExistingProductException.class)
    public ResponseEntity<StandardError> objectNotFound(ExistingProductException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.CONFLICT.value(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
	
	@ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
