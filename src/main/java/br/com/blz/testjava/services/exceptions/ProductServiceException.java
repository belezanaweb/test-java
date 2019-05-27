package br.com.blz.testjava.services.exceptions;

public class ProductServiceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9179213821934467185L;

	public ProductServiceException(String mensagem) {
		super(mensagem);
	}

	public ProductServiceException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
