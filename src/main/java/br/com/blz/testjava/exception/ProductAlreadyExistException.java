package br.com.blz.testjava.exception;

public class ProductAlreadyExistException extends RuntimeException {

    public ProductAlreadyExistException() {
    }

    public ProductAlreadyExistException(String msg) {
        super(msg);
    }
}
