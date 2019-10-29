package br.com.blz.testjava.exception;

public class ProductAlreadyExistException extends RuntimeException {

	public ProductAlreadyExistException() {
		super();
	}

	public ProductAlreadyExistException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ProductAlreadyExistException(final String message) {
		super(message);
	}

	public ProductAlreadyExistException(final Throwable cause) {
		super(cause);
	}
}
