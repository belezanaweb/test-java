package br.com.blz.testjava.services;

import br.com.blz.testjava.model.entities.Product;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Optional<Product> getBySku(Long sku);

    void delete(Product product);

    Product update(Product product);
}
