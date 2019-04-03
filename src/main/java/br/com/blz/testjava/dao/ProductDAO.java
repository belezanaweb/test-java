package br.com.blz.testjava.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

@Repository
public class ProductDAO {
	
	@Autowired
	ProductRepository repo;
	
	public Product delete() {
		return null;
	}
	
	public Product selectBySku(Long sku) {
		return null;
	}
	
	public Product update(Product newProduct) {
		return null;
	}
	
	public Product insert(Product newProduct) {
		return null;
	}
}
