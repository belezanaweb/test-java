package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.ProductDuplicatedException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product findProductBy(Long sku) {
		return repository.findOne(sku);
	}
	
	public Product addProduct(Product product) {
		boolean exists = repository.exists(product.getSku());
		
		if (exists)
			throw new ProductDuplicatedException("There is already a product with this sku");
		
		return repository.save(product);
	}
	
	public Product updateProductBy(Long sku, Product product) {
		boolean exists = repository.exists(sku);
		
		if (exists)
			return repository.save(product);
		
		throw new ProductNotFoundException("Does not exist a product with this sku");
	}
	
	public Iterable<Product> findAll() {
		return repository.findAll();
	}

	public void deleteProductBy(Long sku) {
		boolean exists = repository.exists(sku);
		
		if (exists) {
			repository.delete(sku);
			return;
		}
		
		throw new ProductNotFoundException("Does not exist a product with this sku");
		
	}
}
