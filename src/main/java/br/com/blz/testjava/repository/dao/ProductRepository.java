package br.com.blz.testjava.repository.dao;

import java.util.List;

import br.com.blz.testjava.repository.entity.Product;

public interface ProductRepository {

	public Product insert(Product product);
	public Product update(Product product) ;
	public Product findById(long sku);
	public boolean delete(long sku);
	public List<Product> findAll();
	public void removeAll();
	
}
