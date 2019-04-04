package br.com.blz.testjava.exception;

public class ProductIdAlreadyInUseException extends RuntimeException {

	private static final long serialVersionUID = -5244979316112852163L;

	public ProductIdAlreadyInUseException(String message) {
		super(message);
	}

	public ProductIdAlreadyInUseException(Throwable cause) {
		super(cause);
	}

	public ProductIdAlreadyInUseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductIdAlreadyInUseException(String message, Throwable cause, boolean enableSuppression,boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
