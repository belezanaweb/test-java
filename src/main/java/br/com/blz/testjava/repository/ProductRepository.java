package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Integer> {

    boolean existsBySku(Integer sku);

}
