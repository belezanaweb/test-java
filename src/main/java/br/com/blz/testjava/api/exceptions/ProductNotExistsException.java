package br.com.blz.testjava.api.exceptions;

public class ProductNotExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductNotExistsException(String message) {
		super(message);
	}

	
}
