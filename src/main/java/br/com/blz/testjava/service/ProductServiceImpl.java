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

		Product obj = productRepository.find(product.getSku());
		if (obj != null) {
			throw new BusinessException("O produto já existe cadastrado.");
		}
		productRepository.save(product);
	}

	public void update(Product product) {
		Product obj = productRepository.find(product.getSku());
		if (obj == null) {
			throw new BusinessException("O produto informado não existe cadastrado.");
		}
		productRepository.save(product);
	}

	public void delete(Integer sku) {
		Product obj = productRepository.find(sku);
		if (obj == null) {
			throw new BusinessException("O produto informado não existe cadastrado.");
		}
		productRepository.delete(sku);
	}

	public Product find(Integer sku) {
		Product obj = productRepository.find(sku);
		if (obj == null) {
			throw new BusinessException("O produto informado não existe cadastrado.");
		}
		return obj;
	}
}