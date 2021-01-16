package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.domain.entity.Product;

import java.util.Set;

public interface ProductService {

    Set<Product> findAll();

    Product findBySku(Long sku);

    void create(ProductRequest productRequest);

    Product update(ProductRequest productRequest);

    void delete(Long sku);
}
