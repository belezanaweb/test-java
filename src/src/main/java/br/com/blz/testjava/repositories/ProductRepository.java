package br.com.blz.testjava.repositories;

import br.com.blz.testjava.models.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long> {

    boolean existsBySku(Long sku);
    Product findBySku(Long sku);
}
