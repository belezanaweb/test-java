package br.com.blz.testjava.exception;

public class InvalidIdException extends RuntimeException {
	
	private static final long serialVersionUID = 3360762528909129342L;

	public InvalidIdException(String message) {
		super(message);
	}

	public InvalidIdException(Throwable cause) {
		super(cause);
	}

	public InvalidIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
