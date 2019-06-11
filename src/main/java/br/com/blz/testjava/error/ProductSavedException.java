package br.com.blz.testjava.error;

public class ProductSavedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ProductSavedException() {
		super();
	}

	public ProductSavedException(String msg) {
		super(msg);
	}
}
