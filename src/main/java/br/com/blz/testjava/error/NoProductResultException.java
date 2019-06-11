package br.com.blz.testjava.error;

public class NoProductResultException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoProductResultException() {
		super();
	}
	
	public NoProductResultException(String msg) {
		super(msg);
	}

}
