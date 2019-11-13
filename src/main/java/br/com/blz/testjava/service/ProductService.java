package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;

public interface ProductService {

    Product create(Product product);

    Product findBySku(Long sku);

    Product update(Long sku, Product product);

    void delete(Long sku);
}
