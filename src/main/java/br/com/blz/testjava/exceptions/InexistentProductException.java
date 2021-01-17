package br.com.blz.testjava.exceptions;

public class InexistentProductException extends RuntimeException {
    public InexistentProductException(String message) {
        super(message);
    }
}
