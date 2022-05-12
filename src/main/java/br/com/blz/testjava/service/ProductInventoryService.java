package br.com.blz.testjava.service;

import br.com.blz.testjava.exception.AlreadyExistsException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.ProductResponse;


public interface ProductInventoryService {

    ProductResponse findProduct(Integer id);

    void save(Product product) throws AlreadyExistsException;

    void update(Integer id, Product product);

    void remove(ProductResponse product);
}
