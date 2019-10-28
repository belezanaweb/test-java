package br.com.blz.testjava.exception;

public class ProductNotFoundException extends RuntimeException{
	
	public ProductNotFoundException() {
		super();
	}
	public ProductNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
	
    public ProductNotFoundException(final String message) {
        super(message);
    }

    public ProductNotFoundException(final Throwable cause) {
        super(cause);
    }
}
