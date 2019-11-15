package br.com.blz.testjava.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.ResourceExceptionAlreadyExistsHandler;
import br.com.blz.testjava.exception.ResourceExceptionHandler;
import br.com.blz.testjava.repositories.ProductRepository;

@Service
public class ProductServiceImpl {

	@Autowired
	private ProductRepository productRepo;

	public Product saveProduct(Product product) throws Exception {
		if (productRepo.existsBySku(product.getSku())) {
			throw new ResourceExceptionAlreadyExistsHandler();

		}

		return productRepo.save(product);
	}

	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}

	public Product findBySku(Long sku) throws Exception {
		if (!productRepo.existsBySku(sku)) {
			throw new ResourceExceptionHandler();

		}
		return productRepo.findBySku(sku);
	}

	public void deleteProduct(Long sku) {
		if (!productRepo.existsBySku(sku)) {
			throw new ResourceExceptionHandler();
		}

		Product product = productRepo.findBySku(sku);
		productRepo.delete(product);
	}

}