package br.com.blz.testjava.exceptions;

public class ProductUnavaliableException extends RuntimeException {

    public ProductUnavaliableException(String msg) {
        super(msg);
    }

}
