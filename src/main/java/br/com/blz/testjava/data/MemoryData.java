package br.com.blz.testjava.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.model.Product;

@Component
public class MemoryData {

	HashMap<Long, Product> productMap = new HashMap<Long, Product>();

	public Boolean insert(Product product)  {

		if (!productMap.containsKey(product.getSku())) {
			productMap.put(product.getSku(), product);
			return true;
		}else {
			return false;
		}
	}

	public Product getProductBySku(Long sku) {
		if (productMap.containsKey(sku)) {
			return productMap.get(sku);
		}
		return null;
	}
	
	public Boolean delete(Long sku) {
		if (productMap.containsKey(sku)) {
			productMap.remove(sku);
			return true;
		}
		return false;
	}
	
	public Boolean update(Product product) {
		
		if (productMap.containsKey(product.getSku())) {
			productMap.put(product.getSku(), product);
			return true;
		}
		
		return false;		
	}
	
	public List<Product> getProducts(){
		return new ArrayList<Product>(productMap.values());
	}

}
