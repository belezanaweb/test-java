package br.com.belezaNaWeb.javaTest.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.belezaNaWeb.javaTest.domain.Product;
import br.com.belezaNaWeb.javaTest.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void init() {
		productRepository = new ProductRepository();
	}

	public Product findProductBySku(Long sku) {
		return productRepository.findBySku(sku);
	}

	public void createProduct(Product product) throws Exception {
		if (product == null) {
			throw new Exception("The Product can't be null.");
		}

		Product productExist = productRepository.findBySku(product.getSku());

		if (productExist != null) {
			throw new Exception("The Product with sku " + product.getSku() + " already exists.");
		} else {
			productRepository.create(product);
		}
	}

	public void deleteProduct(Long sku) {
		Product product = productRepository.findBySku(sku);
		if (product != null) {
			productRepository.delete(product);
		}
	}

	public boolean updateProduct(Product product) {
		Product productNew = productRepository.findBySku(product.getSku());
		if (productNew != null) {
			productNew.setName(product.getName());
			productNew.setInventory(product.getInventory());
			productRepository.update(productNew);
		}
		return productNew != null ? true : false;
	}

}
