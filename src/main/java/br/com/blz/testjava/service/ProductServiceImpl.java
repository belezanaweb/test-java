package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product save(Product product) {

		Product obj = productRepository.findProduct(product.getSku());
		if (obj != null) {
			throw new BusinessException("There is already a product with informed sku");
		}
		
		return productRepository.save(product);
	}

	public Product update(Product product) {
		
		Product obj = productRepository.findProduct(product.getSku());
		if (obj == null) {
			throw new BusinessException("Invalid product for Sku in update.");
		}
		
		return productRepository.save(product);
	}

	public void delete(Integer sku) {
		
		Product obj = productRepository.findProduct(sku);
		if (obj == null) {
			throw new BusinessException("Invalid product for Sku in deletion.");
		}
		productRepository.deleteProduct(sku);
		
	}

	public Product find(Integer sku) {
		
		Product obj = productRepository.findProduct(sku);
		if (obj == null) {
			throw new BusinessException("Product not found.");
		}
		return obj;
	}
}
