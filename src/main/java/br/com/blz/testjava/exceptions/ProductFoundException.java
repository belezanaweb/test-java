package br.com.blz.testjava.exceptions;

public class ProductFoundException extends Exception {

    public ProductFoundException(int sku) {
        super(String.format("Already exists a product with sku: %d", sku));
    }
}
