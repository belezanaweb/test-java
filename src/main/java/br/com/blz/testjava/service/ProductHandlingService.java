package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dao.ProductDAO;
import br.com.blz.testjava.model.Product;

@Service
public class ProductHandlingService implements ProductService {
	
	@Autowired
	ProductDAO dao;
	
	public Product deleteProduct(Long sku) {
		return null;
	}
	
	public Product getProductBySKU(Long sku) {
		return null;
	}
	
	public Product postProduct(Product body) {
		return null;
	}
	
	public Product updateProductWithForm(Product newProduct) {
		return null;
	}
}
