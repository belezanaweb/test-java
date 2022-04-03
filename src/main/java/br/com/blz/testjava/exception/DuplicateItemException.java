package br.com.blz.testjava.exception;

public class DuplicateItemException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicateItemException(final String message) {
		super(message);
	}
}
