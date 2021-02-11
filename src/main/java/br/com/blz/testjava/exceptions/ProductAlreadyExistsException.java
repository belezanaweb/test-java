package br.com.blz.testjava.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2319243822173486189L;

	public ProductAlreadyExistsException(Long sku) {
	    super("Product " + sku + " already exists");
	}

}
