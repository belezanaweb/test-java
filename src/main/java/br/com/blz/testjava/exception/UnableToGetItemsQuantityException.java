package br.com.blz.testjava.exception;

public class UnableToGetItemsQuantityException extends RuntimeException {
	
	private static final long serialVersionUID = 5257086606615985143L;

	public UnableToGetItemsQuantityException(String message) {
		super(message);
	}
}
