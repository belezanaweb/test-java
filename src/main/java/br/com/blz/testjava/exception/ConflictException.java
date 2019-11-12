package br.com.blz.testjava.exception;

public class ConflictException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ConflictException() {
        super();
    }
    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
    public ConflictException(String message) {
        super(message);
    }
    public ConflictException(Throwable cause) {
        super(cause);
    }
}
