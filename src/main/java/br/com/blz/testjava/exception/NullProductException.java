package br.com.blz.testjava.exception;

public class NullProductException extends RuntimeException {
	
	private static final long serialVersionUID = -8278843985155078136L;

	public NullProductException(String message) {
		super(message);
	}

	public NullProductException(Throwable cause) {
		super(cause);
	}

	public NullProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullProductException(String message, Throwable cause, boolean enableSuppression,boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
