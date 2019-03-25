package br.com.blz.testjava.exception;

/**
 * Exception to product operations.
 */
public class BlzException extends RuntimeException {

    /**
     * {@inheritDoc}
     */
    public BlzException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public BlzException(String message, Throwable cause) {
        super(message, cause);
    }
}
