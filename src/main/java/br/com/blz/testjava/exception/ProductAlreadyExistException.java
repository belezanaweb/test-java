package br.com.blz.testjava.exception;

public class ProductAlreadyExistException extends RuntimeException{

    private String message;

    public ProductAlreadyExistException() {}

    public ProductAlreadyExistException(String msg) {
        super(msg);
        this.message = msg;
    }
}
