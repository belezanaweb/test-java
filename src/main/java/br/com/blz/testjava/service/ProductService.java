package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.entity.Product;

import java.util.Set;

public interface ProductService {

    Set<Product> findAll();

    void create(ProductRequest productRequest);
}
