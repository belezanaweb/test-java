package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product does not exist")
public class ProductNotFoundException extends Exception {
	public ProductNotFoundException(String s) {
		super(s);
	}
}
