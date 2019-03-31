package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.domain.Product;

@Component
public class ProductRepository {

	private static final Map<Integer, Product> TEMP_DB = new HashMap<Integer, Product>();

	public void save(Product product) {
		TEMP_DB.put(product.getSku(), product);
	}

	public Product find(Integer sku) {
		return TEMP_DB.get(sku);
	}

	public void delete(Integer sku) {
		TEMP_DB.remove(sku);
	}
}
