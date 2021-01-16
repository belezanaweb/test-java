package br.com.blz.testjava.infrastructure.exception;

public class ApplicationException extends RuntimeException {

    protected ApplicationException(String message) {
        super(message);
    }
}
