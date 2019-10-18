package br.com.blz.testjava.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product update(Long sku, Product product) {
		Product productSaved = searchProductBySku(sku);
		BeanUtils.copyProperties(product, productSaved, "sku");
		return productRepository.save(product);
	}
	
	public Product searchProductBySku(Long sku) {
		Optional<Product> productSaved = productRepository.findById(sku);
		if (!productSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return productSaved.get();
	}
}
