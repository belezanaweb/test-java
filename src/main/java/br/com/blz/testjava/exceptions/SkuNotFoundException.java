package br.com.blz.testjava.exceptions;

public class SkuNotFoundException extends RuntimeException {
    public SkuNotFoundException(Long id) {
        super(String.format("Sku %s not found!", id));
    }

}
