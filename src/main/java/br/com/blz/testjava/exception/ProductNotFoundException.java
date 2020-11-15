package br.com.blz.testjava.exception;

public class ProductNotFoundException extends BaseException {

	public ProductNotFoundException() {
		super("Produto não encontrado");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
