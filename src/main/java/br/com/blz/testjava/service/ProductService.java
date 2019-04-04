package br.com.blz.testjava.service;

import java.util.List;

import br.com.blz.testjava.model.Product;

public interface ProductService {
	
	Product deleteProduct(Long sku);
	
	Product getProductBySKU(Long sku);
	
	Product postProduct(Product body);
	
	Product updateProductWithForm(Product newProduct);

	List<Long> testGetAll();
}
