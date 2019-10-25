package br.com.beleza.repository;

public class ProductAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3996635848295564745L;

	public ProductAlreadyExistException(String message) {
		super(message);
	}
	
}
