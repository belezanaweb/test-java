package br.com.blz.testjava.business;

import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.exception.ExistingProductException;
import br.com.blz.testjava.exception.InvalidProductSKUException;
import br.com.blz.testjava.exception.NonExistingProductException;
import br.com.blz.testjava.exception.NullProductException;

public interface ProductService {

    Product createProduct(Product product) throws ExistingProductException, InvalidProductSKUException, NullProductException;

    Product updateProduct(Product product) throws InvalidProductSKUException, NonExistingProductException;

    Product findProduct(String sku) throws InvalidProductSKUException;

    void removeProduct(String sku) throws InvalidProductSKUException, NullProductException;
}
