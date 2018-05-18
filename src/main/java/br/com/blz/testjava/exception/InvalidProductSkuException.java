package br.com.blz.testjava.exception;

public class InvalidProductSkuException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidProductSkuException() {
		super("This sku doesnt not exist for any product");
	}
}
