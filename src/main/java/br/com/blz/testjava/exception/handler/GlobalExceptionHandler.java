package br.com.blz.testjava.exception.handler;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.blz.testjava.exception.BaseException;
import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final  Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> productNotFound(BaseException ex){
		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> productAlreadyExists(BaseException ex){
		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> internalException(RuntimeException ex){
		logger.error(ex.getMessage(), ex);
		return new ResponseEntity<>("Ops!", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> failedValidation(MethodArgumentNotValidException ex){
	    String message = ex.getAllErrors().iterator().next().getDefaultMessage();
	    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	

}
