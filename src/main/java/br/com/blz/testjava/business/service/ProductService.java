package br.com.blz.testjava.business.service;

import java.util.Optional;

import br.com.blz.testjava.business.domain.Product;

public interface ProductService {
	
	Product save(Product product);
	Product update(Product product, Long sku);
	Optional<Product> findBySku(Long sku);
	void deleteBySku(Long sku);
}
