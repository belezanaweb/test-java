package br.com.blz.testjava.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.blz.testjava.entity.Product;

public class ProductRepository {

	private List<Product>listProducts = new ArrayList<>();
	
	private static ProductRepository instance;

	private ProductRepository() {}
	
	public static ProductRepository getInstance() {
		if (instance == null) {
			instance = new ProductRepository();
		}

		return instance;
	}
	
	public void save(Product product) {
		listProducts.add(product);
	}
	
	public void update(Product product, Long sku) {
		 listProducts.stream()
				.filter(p -> p.getSku().equals(sku))
				.forEach(p -> {p.setInventory(product.getInventory()); 
				               p.setName(product.getName());
				               });
	}
	
	public Optional<Product> findBySku(Long sku) {
		return listProducts.stream().filter(product -> product.getSku().equals(sku)).findFirst();
	}
	
	public void delete(Product product) {
		listProducts.remove(product);
	} 
	
}
