package br.com.blz.testjava.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import br.com.blz.testjava.dto.Product;

@Service
public class PagesService {
	private static Map<Long, Product> products = new HashMap<Long, Product>();

	public Product create(Product product) {
		if (products.containsKey(product.getSku())) {
			throw new IllegalStateException();
		}

		return products.put(product.getSku(), product);
	}

	public Product edit(Long sku, Product product) {
		return products.put(sku, product);
	}

	public Product find(Long sku) {
		Product product = products.get(sku);
		if(null != product) {
			product.getInventory().setQuantity();
			product.setMarkatable();
		}
		
		return product;
	}

	public Product delete(Long sku) {
		return products.remove(sku);
	}

}
