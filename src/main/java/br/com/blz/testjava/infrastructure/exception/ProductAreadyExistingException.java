package br.com.blz.testjava.infrastructure.exception;

import br.com.blz.testjava.entity.Product;

public class ProductAreadyExistingException extends RepositoryException {

    public ProductAreadyExistingException(Product product) {
        super(String.format("O produto de SKU %d já está cadastrado.", product.getSku()));
    }

}
