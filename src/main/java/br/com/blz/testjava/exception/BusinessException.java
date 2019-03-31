package br.com.blz.testjava.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 4011458387598089434L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
