package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    ProductEntity findBySku(Long sku);
}
