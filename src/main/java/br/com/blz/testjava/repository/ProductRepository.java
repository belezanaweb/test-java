package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findBySku(@Param("sku") Long sku);

}
