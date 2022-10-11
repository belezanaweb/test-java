package br.com.blz.testjava.exception;

public class ProductNotFoundException extends RuntimeException {

    private String message;

    public ProductNotFoundException() {}

    public ProductNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
