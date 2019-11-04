package br.com.blz.testjava.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository repository = ProductRepository.getInstance();
	
	public void save(Product product) {
		repository.save(product);
	}

	public void update(Product product, Long sku) {
		repository.update(product, sku);
	}
	
	public Optional<Product> findBySku(Long sku) {
		return repository.findBySku(sku);
	}

	public void delete(Product product) {
		repository.delete(product);
	}

}
