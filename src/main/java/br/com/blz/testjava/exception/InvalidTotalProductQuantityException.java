package br.com.blz.testjava.exception;

public class InvalidTotalProductQuantityException extends RuntimeException {
	
	private static final long serialVersionUID = -2436081276477247963L;

	public InvalidTotalProductQuantityException(String message) {
		super(message);
	}
}
