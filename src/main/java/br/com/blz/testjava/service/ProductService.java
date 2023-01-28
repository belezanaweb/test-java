package br.com.blz.testjava.service;

import java.util.List;

import br.com.blz.testjava.repository.entity.Product;

public interface ProductService {

	public Product create(Product product);

	public Product update(Product product);

	public boolean delete(long sku);

	public Product findById(long sku);

	public List<Product> findAll();

}
