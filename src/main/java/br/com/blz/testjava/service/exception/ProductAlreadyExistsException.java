package br.com.blz.testjava.service.exception;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends BusinessException {

	private static final long serialVersionUID = 2154654125809682217L;

	public ProductAlreadyExistsException() {
		super("products-3", HttpStatus.BAD_REQUEST);
	}

}
