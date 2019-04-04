package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST , reason="Unable to compute total quantity of items in stock")  // 400
public class UnableToGetItemsQuantityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnableToGetItemsQuantityException(String message) {
		super(message);
	}

	public UnableToGetItemsQuantityException(Throwable cause) {
		super(cause);
	}

	public UnableToGetItemsQuantityException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToGetItemsQuantityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
