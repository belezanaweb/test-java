package br.com.blz.testjava.repository;

import java.util.List;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.model.Product;

public interface ProductRepositoryInterface {
	public Product save(Product product) throws ProductAlreadyExistsException;
	public Product findBySku(Long sku);
	public void deleteBySku(Long sku);
	public List<Product> findAll();
}
