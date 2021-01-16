package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.Product;

import java.util.Set;

public interface ProductRepository {

    Set<Product> findAll();

    void save(Product product);
}
