package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST) 
public class ProductNotExistsException extends RuntimeException{
	
		private static final long serialVersionUID = 1L;

	    public ProductNotExistsException() {
	        super();
	    }
} 
