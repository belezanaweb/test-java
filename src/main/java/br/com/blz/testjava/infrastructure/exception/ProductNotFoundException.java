package br.com.blz.testjava.infrastructure.exception;

public class ProductNotFoundException extends RepositoryException {

    public ProductNotFoundException(Long sku) {
        super(String.format("O produto de SKU %d não foi encontrado", sku));
    }
}
