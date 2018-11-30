package br.com.blz.testjava.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6111220502851515406L;

	public ProductNotFoundException() {}

	public ProductNotFoundException(String message) {
		super(message);
	}

	
}
