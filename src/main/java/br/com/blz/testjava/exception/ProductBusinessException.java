package br.com.blz.testjava.exception;

public class ProductBusinessException extends RuntimeException {
    public ProductBusinessException(String errorMessage) {
        super(errorMessage);
    }
}
