package br.com.blz.testjava.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.application.ProductService;
import br.com.blz.testjava.domain.exception.ProductAlreadyExistException;
import br.com.blz.testjava.domain.exception.ProductNotFoundException;
import br.com.blz.testjava.domain.model.Product;
import br.com.blz.testjava.domain.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@Override
	public Product save(Product product) {
		verifyIfProductExists(product);
		return productRepository.save(product);
	}
	
	@Override
	public Product update(Product product) {
		findBySku(product.getSku());
		return productRepository.save(product);
	}
	
	@Override
	public Product findBySku(Long sku) {
		return productRepository.findBySku(sku).orElseThrow(() -> new ProductNotFoundException());
	}
	
	private void verifyIfProductExists(final Product product) {
        
		Optional<Product> productBySku = productRepository.findBySku(product.getSku());
		
        if (productBySku.isPresent() && (productBySku.get().equals(product))) {
            throw new ProductAlreadyExistException();
        }
    }
	
	@Override
	public void remove(Long sku) {
		Product product = findBySku(sku);
		productRepository.delete(product);
	}
}
