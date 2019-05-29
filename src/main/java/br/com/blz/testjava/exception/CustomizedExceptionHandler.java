package br.com.blz.testjava.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class CustomizedExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<Error> handleBusinessExceptions(BusinessException ex, WebRequest request) {
	  Error errorDetails = new Error(new Date(), ex.getMessage(),
	      request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
}
