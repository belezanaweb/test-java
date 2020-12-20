package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.entity.Product;
import java.util.List;


public interface ProductRepository {

    List<Product> findAll();

    Product find(Long sku);

    Product delete(Long sku);

    Product save(Product product);

    Product update(Product product);

    void clear();

}
