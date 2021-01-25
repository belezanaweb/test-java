package br.com.blz.testjava.service;

public class ProductDuplicateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3055204702230675171L;

	public ProductDuplicateException(final Long sku ) {
		super("A SKU " + sku );
	} 

}
