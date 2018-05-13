package br.com.blz.testjava.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Product;

@Repository
public class ProductRepository {

	private static Map<String, Product> products = new HashMap<>();

	public void createProduct(Product product) {
		products.put(product.getSku(), product);
	}

	public void deleteProduct(String sku) {
		products.remove(sku);
	}

	public Product findProduct(String sku) {
		return products.get(sku);
	}

	public void updateProduct(Product product) {
		products.put(product.getSku(), product);
	}

	public List<Product> findProducts() {
		return new ArrayList<Product>(products.values());	
	}
}
