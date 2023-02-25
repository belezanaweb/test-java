package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;

import java.util.List;
import java.util.Optional;

public interface Repository {

    public Product save(Product product);

    public Optional<Product> findBySku(Long sku);

    public void deleteBySku(Long sku);

    public Product update(Product productToUpdate);

    public Boolean exists(Product product);

    public List<Product> findAll();

    public int count();

}
