package br.com.blz.testjava.exceptionhandler.exception;

public class ProductAlreadyExistException extends RuntimeException { 
	
	private static final long serialVersionUID = 368431488011860611L;

	public ProductAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
	
	public ProductAlreadyExistException() {
        super();
    }
}
