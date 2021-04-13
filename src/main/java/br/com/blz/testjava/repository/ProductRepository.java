package br.com.blz.testjava.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.testjava.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	public Product findBySku(Long sku);
	
}
