package br.com.blz.testjava.exception;

public final class EntityInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EntityInvalidException(String message) {
		super(message);
	}
	
	public EntityInvalidException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
