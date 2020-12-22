package br.com.blz.testjava.persistence.repository;

import br.com.blz.testjava.persistence.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, Long> {
    Optional<Product> findBySku(Long sku);
}
