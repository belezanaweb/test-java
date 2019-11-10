package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.model.Product;

/**
 * 
 * @author andrey
 * @since 2019-11-10
 */
@Component
public class ProductRepository {

	private static final Map<Integer, Product> MAP_PRODUCT = new HashMap<Integer, Product>();

	public Product findBy(Integer sku) {
		return MAP_PRODUCT.get(sku);
	}
	
	public void save(Product product) {
		MAP_PRODUCT.put(product.getSku(), product);
	}
	
	public void delete(Integer sku) {
		MAP_PRODUCT.remove(sku);
	}
}