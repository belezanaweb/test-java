package br.com.blz.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	public void createProduct(Product product) throws BusinessException {
		if (repository.findProducts().contains(product)) {
			throw new BusinessException("Produto já existe");
		} else {
			repository.createProduct(product);
		}
	}
	
	public void deleteProduct(String sku) {
		repository.deleteProduct(sku);
	}
	
	public Product findProduct(String sku) {
		return repository.findProduct(sku);
	}
	
	public void updateProduct(Product product) {
		repository.updateProduct(product);
	}
	
	public List<Product> findProducts() {
		return repository.findProducts();	
	}

}
