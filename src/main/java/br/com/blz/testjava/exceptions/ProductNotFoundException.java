package br.com.blz.testjava.exceptions;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7635191352656732647L;

	public ProductNotFoundException(Long sku) {
	    super("Could not find product " + sku);
  }

}
