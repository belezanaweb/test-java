package br.com.blz.testjava.controller.advice;

public class Error {
    private final String message;

    public Error(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
