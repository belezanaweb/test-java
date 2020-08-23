package br.com.blz.testjava.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(Long id) {
        super(String.format("Product %s already exists!", id));
    }

}
