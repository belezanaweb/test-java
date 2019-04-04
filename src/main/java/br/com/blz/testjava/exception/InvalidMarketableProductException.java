package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED , reason="Marketable must be true or false")  // 412
public class InvalidMarketableProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidMarketableProductException(String message) {
		super(message);
	}

	public InvalidMarketableProductException(Throwable cause) {
		super(cause);
	}

	public InvalidMarketableProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidMarketableProductException(String message, Throwable cause, boolean enableSuppression,boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
