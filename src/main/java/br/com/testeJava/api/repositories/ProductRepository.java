package br.com.testeJava.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.testeJava.api.documents.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	Product findBySku(String sku);
	
}
