package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.models.Product;

@Component
public class ProductRepository {

	private static final Map<Integer, Product> MAP_FAKE_DB = new HashMap<Integer, Product>();

	public void save(Product product) {
		MAP_FAKE_DB.put(product.getSku(), product);
	}

	public Product findProduct(Integer sku) {
		return MAP_FAKE_DB.get(sku);
	}

	public void deleteProduct(Integer sku) {
		MAP_FAKE_DB.remove(sku);
	}
}
