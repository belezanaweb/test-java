package br.com.blz.testjava.exception;

public class NullProductException extends RuntimeException {
	
	private static final long serialVersionUID = -8278843985155078136L;

	public NullProductException(String message) {
		super(message);
	}
}
