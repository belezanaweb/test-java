package br.com.blz.testjava.api.exceptions;

public class ProductAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistsException(String message) {
		super(message);
	}
}
