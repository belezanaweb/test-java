package br.com.blz.testjava.exception;

public class InvalidQuantityInventoryLinkException extends RuntimeException {
	
	private static final long serialVersionUID = -5399690267774599641L;

	public InvalidQuantityInventoryLinkException(String message) {
		super(message);
	}
}
