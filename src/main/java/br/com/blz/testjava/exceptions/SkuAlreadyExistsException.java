package br.com.blz.testjava.exceptions;

public class SkuAlreadyExistsException extends RuntimeException {
    public SkuAlreadyExistsException(Long id) {
        super(String.format("Sku %s already exists!", id));
    }

}
