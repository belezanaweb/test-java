package br.com.blz.testjava.service;

import br.com.blz.testjava.dao.entity.Product;

public interface IProductService {

	Iterable<Product> findAll();

	Product findById(Long sku);

	Product create(Product product);

	void delete(Long sku);

	Product update(Long sku, Product product);
}
