package br.com.blz.testjava.exceptions;

public class ProductAlredyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_PRODUTO_ALREAY_EXISTS = "Produto já existente";
	
	public ProductAlredyExistsException() {
		super(MESSAGE_PRODUTO_ALREAY_EXISTS);
		
	}

	
	
}
