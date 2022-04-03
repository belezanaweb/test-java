package br.com.blz.testjava.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusinessException(final String message) {
		super(message);
	}
}
