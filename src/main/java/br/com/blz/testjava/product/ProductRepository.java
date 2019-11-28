package br.com.blz.testjava.product;

import java.util.Optional;

public interface ProductRepository {
	
	Optional<Product> save(Product product);
	
	Optional<Product> findBySku(Long sku);
	
	Optional<Product> deleteBySku(Long sku);

	Boolean hasBySku(Long sku);

}
