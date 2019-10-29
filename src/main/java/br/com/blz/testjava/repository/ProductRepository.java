package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.entity.Product;

@Component
public class ProductRepository {

	private static final Map<Integer, Product> DATABASE_MEMORY = new HashMap<Integer, Product>();

	public Product save(Product product) {
		DATABASE_MEMORY.put(product.getSku(), product);
		return product;
	}

	public Product findProduct(Integer sku) {
		return DATABASE_MEMORY.get(sku);
	}

	public void deleteProduct(Integer sku) {
		DATABASE_MEMORY.remove(sku);
	}
}

