package br.com.blz.testjava.repository;

import java.util.Optional;

import br.com.blz.testjava.entity.Product;

public interface ProductRepository {

    public Optional<Product> getBySku(Long sku);

    public Product save(Product product);

    public Product update(Product product);

    public boolean delete(Long sku);

}
