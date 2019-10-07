package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class SkuAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 8220152638260565404L;

	public SkuAlreadyExistsException() {
		super();
	}

	public SkuAlreadyExistsException(String message) {
		super(message);
	}
}
