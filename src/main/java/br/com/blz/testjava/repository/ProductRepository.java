package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.entity.Product;

import java.util.Set;

public interface ProductRepository {

    Set<Product> findAll();

    Product findBySku(Long sku);

    void save(Product product);

    void update(Product product);

    void delete(Long sku);
}
