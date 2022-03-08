package br.com.blz.testjava.exceptions;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(int sku) {
        super(String.format("There isn't a product with sku: %d", sku));
    }
}
