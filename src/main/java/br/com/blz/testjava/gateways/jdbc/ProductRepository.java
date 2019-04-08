package br.com.blz.testjava.gateways.jdbc;

import br.com.blz.testjava.domains.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Integer> {

    boolean existsBySku(Integer sku);

}
