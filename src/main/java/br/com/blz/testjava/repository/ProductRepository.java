package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.models.Product;

@Component
public class ProductRepository {

	private static final Map<Integer, Product> MAP_BLZWEB_DB = new HashMap<Integer, Product>();

	public void save(Product product) {
		MAP_BLZWEB_DB.put(product.getSku(), product);
	}

	public Product findProduct(Integer sku) {
		return MAP_BLZWEB_DB.get(sku);
	}

	public void deleteProduct(Integer sku) {
		MAP_BLZWEB_DB.remove(sku);
	}
}
