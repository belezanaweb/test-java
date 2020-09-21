package br.com.blz.testjava.exception;

public class DuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicatedException(Integer key) {
		super("Este SKU já existe! - " + key);
	}

}
