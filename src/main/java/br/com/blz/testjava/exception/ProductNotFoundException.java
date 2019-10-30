package br.com.blz.testjava.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer sku) {
        super("Product with sku not found " + sku);
    }
}
