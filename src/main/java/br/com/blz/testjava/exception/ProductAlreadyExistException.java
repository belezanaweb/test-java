package br.com.blz.testjava.exception;

public class ProductAlreadyExistException extends Exception {

    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
