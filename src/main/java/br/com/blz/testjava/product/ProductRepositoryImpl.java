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
		return this.findBySku(product.getSku());
	}
	
	@Override
	public Product update(Product product) {
		Product p = this.findBySku(product.getSku());
		if(p == null) {
			//lanca exe
		}
		this.delete(product.getSku());
		this.save(product);
		return this.findBySku(product.getSku());
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
