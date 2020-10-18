package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;

public interface ProductService extends BaseService<Product, Long> {

    Product findBySku(Long sku);

    void deleteBySku(Long sku);

    Product updateBySku(Long sku, Product entity);
}
