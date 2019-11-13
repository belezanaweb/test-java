package br.com.blz.testjava.exception;

public class BadRequestException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super();
    }
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
