package br.com.blz.testjava.exception;

public class InvalidProductNameException extends RuntimeException {

	private static final long serialVersionUID = 3278804861033988207L;

	public InvalidProductNameException(String message) {
		super(message);
	}

	public InvalidProductNameException(Throwable cause) {
		super(cause);
	}

	public InvalidProductNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidProductNameException(String message, Throwable cause, boolean enableSuppression,boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
