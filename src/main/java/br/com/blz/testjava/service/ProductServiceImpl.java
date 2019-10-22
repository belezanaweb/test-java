package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void save(Product product) {

		Product obj = productRepository.findProduct(product.getSku());
		if (obj != null) {
			throw new BusinessException("Product Already Exists");
		}
		productRepository.save(product);
	}

	public void update(Product product) {
		Product obj = productRepository.findProduct(product.getSku());
		if (obj == null) {
			throw new BusinessException("Product Invalid with the Sku informed");
		}
		productRepository.save(product);
	}

	public void delete(Integer sku) {
		Product obj = productRepository.findProduct(sku);
		if (obj == null) {
			throw new BusinessException("Product Invalid with the Sku informed");
		}
		productRepository.deleteProduct(sku);
	}

	public Product find(Integer sku) {
		Product obj = productRepository.findProduct(sku);
		if (obj == null) {
			throw new BusinessException("Product Invalid with the Sku informed");
		}
		return obj;
	}
}