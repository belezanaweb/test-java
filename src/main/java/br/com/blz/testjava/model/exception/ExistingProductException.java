package br.com.blz.testjava.model.exception;

public class ExistingProductException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ExistingProductException(Long sku) {
		super("This product already exists: " + sku);
	}
	
}
