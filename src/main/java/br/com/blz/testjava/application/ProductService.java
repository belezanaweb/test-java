package br.com.blz.testjava.application;

import java.util.List;

import br.com.blz.testjava.domain.model.Product;

public interface ProductService {
	List<Product> findAll();
	Product save(Product product);
	Product findBySku(Long sku);
	Product update(Product product);
	void remove(Long sku);
}
