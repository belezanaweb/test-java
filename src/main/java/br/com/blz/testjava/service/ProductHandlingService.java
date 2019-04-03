package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dao.ProductDAO;
import br.com.blz.testjava.model.Product;

@Service
public class ProductHandlingService implements ProductService {
	
	@Autowired
	ProductDAO dao;
	
	public Product postProduct(Product newProduct) {
		return dao.insert(newProduct);
	}
	
	public Product getProductBySKU(Long sku) {
		return dao.selectBySku(sku);
	}
	
	public Product updateProductWithForm(Product newProduct) {
		return dao.update(newProduct);
	}
	
	public Product deleteProduct(Long sku) {
		return dao.delete(sku);
	}
}
