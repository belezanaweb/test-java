package br.com.blz.testjava.exception;

public class InvalidIdException extends RuntimeException {
	
	private static final long serialVersionUID = 3360762528909129342L;

	public InvalidIdException(String message) {
		super(message);
	}
}
