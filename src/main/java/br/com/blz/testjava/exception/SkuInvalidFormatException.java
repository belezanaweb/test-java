package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.EXPECTATION_FAILED)
public class SkuInvalidFormatException extends RuntimeException {
	private static final long serialVersionUID = -2800343236377660151L;

	public SkuInvalidFormatException() {
		super();
	}

	public SkuInvalidFormatException(String message) {
		super(message);
	}
}
