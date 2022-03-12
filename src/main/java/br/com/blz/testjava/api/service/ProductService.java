package br.com.blz.testjava.api.service;

import br.com.blz.testjava.api.domain.Product;

public interface ProductService {

    void save(Product product);

    Product findProductBySku(Integer sku);

    void update(Product product, Integer sku);

    void delete(Integer sku);
}
