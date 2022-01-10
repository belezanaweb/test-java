package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.domain.Product;

@Component
public class ProductRepository {
	final Map<Integer, Product> mapProducts = new HashMap<Integer, Product>();
			
	public void save(final Product product) {
		mapProducts.put(product.getSku(), product);
	}
	
	public void update(final Product product) {
		mapProducts.replace(product.getSku(), product);
	}
	
	public Product findBySku(final Integer sku) {
		return mapProducts.get(sku);
	}
	
	public void delete(final Integer sku) {
		boolean existsProductInMemory = mapProducts.containsKey(sku);
		
		if(existsProductInMemory) {
			mapProducts.remove(sku);
		}
	}
	
	public boolean existsProduct(final Integer sku) {
		return mapProducts.containsKey(sku);
	}
	
}
