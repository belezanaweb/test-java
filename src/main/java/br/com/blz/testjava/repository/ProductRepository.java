package br.com.blz.testjava.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Product;

@Repository
public class ProductRepository {
	
	private List<Product> productList;

	public ProductRepository() {
		
		productList = new ArrayList<Product>();	
		
	}

	public List<Product> findAll() {
		
		return productList;
		
	}
	
	public Product findById(Long id) {
		
		return productList.stream().filter(product -> product.getSku() == id).findFirst().orElse(null);
		
	}
	
	public boolean create(Product product) {
		
		return productList.add(product);
		
	}
	
	public void update(Product product) {
		
		Product updateProduct = findById(product.getSku());
		updateProduct.setName(product.getName());
		updateProduct.setInventory(product.getInventory());		
		productList.remove(product);
		productList.add(updateProduct);
		
	}
	
	public void delete(Product product) {
		
		productList.remove(product);
		
	}
	
	
}
