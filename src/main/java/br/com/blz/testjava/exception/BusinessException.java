package br.com.blz.testjava.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -7291796666933510339L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
