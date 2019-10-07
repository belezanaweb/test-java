package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class SkuNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2800343236377660151L;

	public SkuNotFoundException() {
		super();
	}

	public SkuNotFoundException(String message) {
		super(message);
	}
}
