package br.com.blz.testjava.exception;

public class ProductAlreadyExistsException extends BaseException {

	public ProductAlreadyExistsException() {
		super("Produto duplicado");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
