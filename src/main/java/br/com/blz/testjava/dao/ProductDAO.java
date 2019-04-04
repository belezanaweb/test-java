package br.com.blz.testjava.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

@Repository
public class ProductDAO {
	
	@Autowired
	ProductRepository repo;
	
	public Product insert(Product newProduct) {
		return repo.insert(newProduct);
	}
	
	public Product selectBySku(Long sku) {
		return repo.get(sku);
	}
	
	public Product update(Product newProduct) {
		return repo.update(newProduct);
	}
	
	public Product delete(Long sku) {
		return repo.delete(sku);
	}

	public List<Long> testGetAll() {
		return repo.testGetAll();
	}
}
