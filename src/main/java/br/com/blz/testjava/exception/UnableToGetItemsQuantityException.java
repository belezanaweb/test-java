package br.com.blz.testjava.exception;

public class UnableToGetItemsQuantityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnableToGetItemsQuantityException(String message) {
		super(message);
	}
}
