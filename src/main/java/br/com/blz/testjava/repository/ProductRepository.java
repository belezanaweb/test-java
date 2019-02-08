package br.com.blz.testjava.repository;

import java.util.Collection;

import br.com.blz.testjava.model.Product;

public interface ProductRepository {
	
	public void create(Product p);
	public void update(Long id,Product p);
	public Collection<Product> getall();
	public Product get(Long id);
	public void delete(Long id);

}
