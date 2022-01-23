package br.com.blz.testjava.domain.repositories;

import java.util.List;

import br.com.blz.testjava.domain.entities.Product;

public interface IProductRepository {
	
	public String add(Product product);
	public String update(Product product, String sku);
	public Boolean delete(String sku);
	public List<Product> findAll(); 
	public Product find(String sku);
}
