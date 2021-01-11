package br.com.blz.testjava.model.exception;

public class ObjectNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(Long sku) {
		super("Product not found: " + sku);
	}

}
