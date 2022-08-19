package br.com.blz.testjava.execption;

public class ProductAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1037153890834099995L;

	public ProductAlreadyExistException(String msg) {
		super(msg);
	}
}
