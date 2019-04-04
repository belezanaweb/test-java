package br.com.blz.testjava.exception;

public class InvalidTotalProductQuantityException extends RuntimeException {
	
	private static final long serialVersionUID = -2436081276477247963L;

	public InvalidTotalProductQuantityException(String message) {
		super(message);
	}

	public InvalidTotalProductQuantityException(Throwable cause) {
		super(cause);
	}

	public InvalidTotalProductQuantityException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidTotalProductQuantityException(String message, Throwable cause, boolean enableSuppression,boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
