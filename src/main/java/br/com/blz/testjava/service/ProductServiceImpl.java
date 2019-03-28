package br.com.blz.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repository.dao.ProductRepository;
import br.com.blz.testjava.repository.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product create(Product product) {
		Product productFounded = productRepository.findById(product.getSku());
		if (productFounded == null) {
			return productRepository.insert(product);
		} else {
			throw new ProductAlreadyExistsException(product.getSku());
		}
	}
	
	public Product update(Product product) {
		Product productFounded = productRepository.findById(product.getSku());
		if (productFounded == null) {
			throw new ProductNotFoundException(product.getSku());
		}
		
		return productRepository.update(product);
	}

	public boolean delete(long sku) {
		if (!productRepository.delete(sku)) {
			throw new ProductNotFoundException(sku);
		}
		
		return true;
	}

	public Product findById(long sku) {
		return productRepository.findById(sku);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

}
