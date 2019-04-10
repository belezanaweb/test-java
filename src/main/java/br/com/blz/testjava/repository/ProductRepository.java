package br.com.blz.testjava.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.testjava.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	public Product findBySku(int sku);
	
}
