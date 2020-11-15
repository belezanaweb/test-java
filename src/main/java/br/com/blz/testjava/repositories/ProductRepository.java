package br.com.blz.testjava.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.testjava.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
	Product findBySku(Long sku);
	Long deleteBySku(Long sku);

}
