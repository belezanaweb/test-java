package br.com.blz.testjava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ProductDuplicatedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProductDuplicatedException(String message) {
		super(message);
	}

	public ProductDuplicatedException() {}
	
}