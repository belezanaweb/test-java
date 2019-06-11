package br.com.blz.testjava.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ProductRepositoryImpl implements ProductRepository {

	private static Map<Long, Product> products = new HashMap<Long, Product>();
	
	@Override
	public Product save(Product product) {
		products.put(product.getSku(), product);
		return product;
	}
	
	@Override
	public Product update(Product product) {
		this.delete(product.getSku());
		return this.save(product);
	}

	@Override
	public Product findBySku(Long sku) {
		Product product = null;
		if(products.containsKey(sku)) {
			product = products.get(sku);
		}
		return product;
	}

	@Override
	public void delete(Long sku) {
		products.remove(sku);
	}

	
	
}
