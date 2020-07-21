package br.com.blz.testjava.product.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.testjava.product.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findBySku(Long sku);
	
	void deleteBySku(Long sku);
}
