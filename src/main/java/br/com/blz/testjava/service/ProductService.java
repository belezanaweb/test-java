package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.model.Product;

import java.util.List;

public interface ProductService {

  Product findBySku(Long sku);

  List<Product> listAll();

  Product create(Product product);

  Product update(Product product);

  void remove(Long sku);
}
