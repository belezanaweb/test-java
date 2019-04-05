package br.com.blz.testjava.exception;

public class DupItemsInWarehousesException extends RuntimeException {
	
	private static final long serialVersionUID = 7248591561599328503L;

	public DupItemsInWarehousesException(String message) {
		super(message);
	}
}
