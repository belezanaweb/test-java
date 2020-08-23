package br.com.blz.testjava.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("Product %s not found!", id));
    }

}
