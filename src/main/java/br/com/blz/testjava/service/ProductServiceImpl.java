package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.utils.Constants;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void save(Product product) {

		Product obj = productRepository.findProduct(product.getSku());
		if (obj != null) {
			throw new BusinessException(Constants.PRODUCT_EXISTS);
		}
		productRepository.save(product);
	}

	public void update(Product product) {
		Product obj = productRepository.findProduct(product.getSku());
		if (obj == null) {
			throw new BusinessException(Constants.PRODUCT_INVALID);
		}
		productRepository.save(product);
	}

	public void delete(Integer sku) {
		Product obj = productRepository.findProduct(sku);
		if (obj == null) {
			throw new BusinessException(Constants.PRODUCT_INVALID);
		}
		productRepository.deleteProduct(sku);
	}

	public Product search(Integer sku) {
		Product obj = productRepository.findProduct(sku);
		if (obj == null) {
			throw new BusinessException(Constants.PRODUCT_INVALID);
		}
		return obj;
	}
}