package br.com.blz.testjava.exception;

public class ProductSKUMismatchException extends RuntimeException{
	public ProductSKUMismatchException() {
		super();
	}

	public ProductSKUMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }
	
    public ProductSKUMismatchException(final String message) {
        super(message);
    }

    public ProductSKUMismatchException(final Throwable cause) {
        super(cause);
    }
}
