package br.com.blz.testjava.exception;

public class DuplicationException extends Exception {

    private static final long serialVersionUID = 6446125212796656491L;

    public DuplicationException(String message) {
        super(message);
    }
}