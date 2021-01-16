package br.com.blz.testjava.infrastructure.exception;

public class ProductAreadyExistingException extends RepositoryException {

    public ProductAreadyExistingException(Long sku) {
        super(String.format("O produto de SKU %d já está cadastrado.", sku));
    }

}
