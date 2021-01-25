package br.com.blz.testjava.service;

public class ProductNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1431547433331733881L;

	public ProductNotFound(final Long sku ) {
		super("SKU " + sku  );
	} 

}
