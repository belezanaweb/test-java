package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product already registered")
public class ProductAlreadyRegisteredException extends Exception {
	public ProductAlreadyRegisteredException(String s) {
		super(s);
	}
}
