package br.com.blz.testjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.IProductRepository;

@Service
public class ProductService {

	private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private IProductRepository productRepository;

	public void createProduct(Product product) {
		
		LOG.info("Validação de SKU já existente");
		validExistProduct(product);
		
		LOG.info("Gravando o Produto");
		productRepository.save(product);

	}

	private void validExistProduct(Product product) {
		if (productRepository.existsById(product.getSku())) {
			throw new ProductDuplicateException(product.getSku());
		}
	}
	

}
