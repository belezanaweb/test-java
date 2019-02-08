package br.com.blz.testjava.exception;

public class ProductAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ProductAlreadyExistsException(String message) {
		super(message);
	}
	
	

}
