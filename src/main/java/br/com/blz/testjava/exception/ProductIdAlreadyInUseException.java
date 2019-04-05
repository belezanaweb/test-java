package br.com.blz.testjava.exception;

public class ProductIdAlreadyInUseException extends RuntimeException {

	private static final long serialVersionUID = -5244979316112852163L;

	public ProductIdAlreadyInUseException(String message) {
		super(message);
	}
}
