package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product findProductBy(int sku) {
		return repository.findBy(sku);
	}
	
	public Product addProduct(Product product) {
		return repository.addProduct(product);
	}

	public void deleteProductBy(int sku) {
		repository.deleteProductBy(sku);
	}

	public Product updateProductBy(int sku, Product product) {
		return repository.updateProduct(sku, product);
	}
	
}
