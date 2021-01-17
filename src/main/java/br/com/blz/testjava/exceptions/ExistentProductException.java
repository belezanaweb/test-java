package br.com.blz.testjava.exceptions;

public class ExistentProductException extends RuntimeException {
    public ExistentProductException(String message) {
        super(message);
    }
}
