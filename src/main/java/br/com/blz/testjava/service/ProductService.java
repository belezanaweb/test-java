package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exepctions.BlzException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

/**
 * 
 * @author andrey
 * @since 2019-11-10
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void save(Product product) {
		Product bean = productRepository.findBy(product.getSku());
		if (bean != null) {
			throw BlzException.newInstance("O produto já existe.");
		}
		productRepository.save(product);
	}

	public void update(Product product) {
		Product bean = productRepository.findBy(product.getSku());
		if (bean == null) {
			throw BlzException.newInstance("O produto %s não existe.",product.getSku());
		}
		productRepository.save(product);
	}

	public void delete(Integer sku) {
		Product bean = productRepository.findBy(sku);
		if (bean == null) {
			throw BlzException.newInstance("O produto %s não existe.",sku);
		}
		productRepository.delete(sku);
	}

	public Product find(Integer sku) {
		Product bean = productRepository.findBy(sku);
		if (bean == null) {
			throw BlzException.newInstance("Produto não encontrado para o sku %s.", sku);
		}
		return bean;
	}
}