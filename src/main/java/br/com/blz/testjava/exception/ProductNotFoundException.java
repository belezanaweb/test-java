package br.com.blz.testjava.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(long sku) {
		super(String.format("Produto %d não existe", sku));
	}

}
