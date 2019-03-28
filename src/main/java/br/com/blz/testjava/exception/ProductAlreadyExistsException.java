package br.com.blz.testjava.exception;

public class ProductAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistsException(long sku) {
		super(String.format("Produto %d já existe", sku));
	}

}
