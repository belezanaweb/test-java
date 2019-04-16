package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product) throws Exception;

    Product find(String sku);

    Product update(String sku, Product product);

    void remove(String sku);

    List<Product> findAll();
}
