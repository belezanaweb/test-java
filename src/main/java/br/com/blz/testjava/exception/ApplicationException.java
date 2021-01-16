package br.com.blz.testjava.exception;

public class ApplicationException extends RuntimeException {

    protected ApplicationException(String message) {
        super(message);
    }
}
