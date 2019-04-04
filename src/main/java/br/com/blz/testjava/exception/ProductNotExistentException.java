package br.com.blz.testjava.exception;

public class ProductNotExistentException extends RuntimeException {
	
	private static final long serialVersionUID = 4718687470669733468L;
	
	public ProductNotExistentException(String message) {
		super(message);
	}
	
	public ProductNotExistentException(Throwable cause) {
		super(cause);
	}
	
	public ProductNotExistentException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ProductNotExistentException(String message, Throwable cause, boolean enableSuppression,boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
