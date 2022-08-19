package br.com.blz.testjava.execption;

public class ProductNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8236799305511164868L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
