package br.com.blz.testjava.exception;

public class InvalidQuantityInventoryLinkException extends RuntimeException {
	
	private static final long serialVersionUID = -5399690267774599641L;

	public InvalidQuantityInventoryLinkException(String message) {
		super(message);
	}

	public InvalidQuantityInventoryLinkException(Throwable cause) {
		super(cause);
	}

	public InvalidQuantityInventoryLinkException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidQuantityInventoryLinkException(String message, Throwable cause, boolean enableSuppression,boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
