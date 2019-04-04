package br.com.blz.testjava.exception;

public class ProductNotExistentException extends RuntimeException {
	
	private static final long serialVersionUID = 4718687470669733468L;
	
	public ProductNotExistentException(String message) {
		super(message);
	}
}
