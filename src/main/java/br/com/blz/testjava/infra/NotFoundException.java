package br.com.blz.testjava.infra;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -469484341088039535L;
	private static final String MESSAGE = "%s NOT FOUND";
	
	public NotFoundException(String notFoundObject) {
		super(String.format(MESSAGE, notFoundObject));
	}
}
