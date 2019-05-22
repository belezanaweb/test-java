package br.com.blz.testjava.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.Products;
import br.com.blz.testjava.service.exception.ProductAlreadyExistsException;

@Service
public class ProductService {

	@Autowired
	private Products products;

	public ProductService(@Autowired Products products) {
		this.products = products;
	}

	public Product save(final Product product) {

		Optional<Product> productBySku = products.findBySku(product.getSku());

		if (productBySku.isPresent()) {
			throw new ProductAlreadyExistsException();
		}

		return products.save(product);
	}

	public void delete(final Integer sku) {

		Optional<Product> productBySku = products.findBySku(sku);

		if (!productBySku.isPresent()) {
			throw new EntityNotFoundException();
		}

		products.delete(productBySku.get());
	}

	public Product get(final Integer sku) {

		Optional<Product> productBySku = products.findBySku(sku);

		if (!productBySku.isPresent()) {
			throw new EntityNotFoundException();
		}

		return productBySku.get();
	}

}
