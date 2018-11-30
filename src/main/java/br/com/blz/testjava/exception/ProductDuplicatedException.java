package br.com.blz.testjava.exception;

public class ProductDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = -2581484724094075660L;

	public ProductDuplicatedException() {}

	public ProductDuplicatedException(String message) {
		super(message);
	}

}
