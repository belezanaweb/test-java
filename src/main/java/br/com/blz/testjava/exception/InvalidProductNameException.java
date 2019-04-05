package br.com.blz.testjava.exception;

public class InvalidProductNameException extends RuntimeException {

	private static final long serialVersionUID = 3278804861033988207L;

	public InvalidProductNameException(String message) {
		super(message);
	}
}
