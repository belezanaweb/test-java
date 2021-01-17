package br.com.blz.testjava.exception;

public class ProductAreadyExistException extends RepositoryException {

    public ProductAreadyExistException(Long sku) {
        super(String.format("O produto de SKU %d já está cadastrado.", sku));
    }

}
