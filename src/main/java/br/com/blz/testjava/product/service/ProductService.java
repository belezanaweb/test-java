package br.com.blz.testjava.product.service;

import br.com.blz.testjava.product.model.Product;
import br.com.blz.testjava.product.util.ProductException;

public interface ProductService {

	void create(Product product) throws ProductException;
	
	void delete(Long sku);
	
	Product update(Long sku, Product product);
	
	Product find(Long sku);
}
