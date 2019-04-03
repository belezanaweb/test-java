package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;

public interface ProductService {
	
	Product deleteProduct(Long sku);
	
	Product getProductBySKU(Long sku);
	
	Product postProduct(Product body);
	
	Product updateProductWithForm(Product newProduct);
}
