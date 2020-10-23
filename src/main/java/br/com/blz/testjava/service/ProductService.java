package br.com.blz.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		
		super();
		productRepository = new ProductRepository();
		
	}
	
	public List<Product> findAll() {
		
		return productRepository.findAll();
		
	}
	
	public Product findById(Long id) {
		
		return productRepository.findById(id);
		
	}
	
	public void create(Product product) throws Exception {
		
		if (product == null) {
			
			throw new Exception("Product must not be null");
			
		}

		Product idValid = productRepository.findById(product.getSku());

		if (idValid != null) {
			
			throw new Exception("Id invalid");
			
		} else {
			
			productRepository.create(product);
			
		}
		
	}
	
	public boolean updateProduct(Product product) {
		
		Product update = productRepository.findById(product.getSku());
		
		if (update != null) {
			
			update.setName(product.getName());
			productRepository.update(update);
			
		}
		
		return update != null ? true : false;
	}
	
	public void deleteProduct(Long id) {
		
		Product deleteProduct = productRepository.findById(id);
		
		if (deleteProduct != null) {
			
			productRepository.delete(deleteProduct);
			
		}
		
	}
	
}
