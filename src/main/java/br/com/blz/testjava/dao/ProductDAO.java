package br.com.blz.testjava.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.ProductAlreadyExists;
import br.com.blz.testjava.exception.ProductNotFound;
import br.com.blz.testjava.model.Product;

@Service
public class ProductDAO {

	private List<Product> products;

	public ProductDAO() {
		products = new ArrayList<Product>();
	}

	public List<Product> getAll() {
		return this.products;
	}

	public Product save(Product product) throws ProductAlreadyExists {

		if (this.products.contains(product)) {
			throw new ProductAlreadyExists();
		}

		this.products.add(product);
		return product;
	}

	public Product findById(Long sku) throws ProductNotFound {
		try {
			
			return this.products.stream().filter(product -> product.getSku().equals(sku)).findFirst().get();
		
		} catch (NoSuchElementException e) {
			throw new ProductNotFound();
		}
	}

	public Product update(Long sku, Product product) throws ProductNotFound {
		Product productUpdate = new Product();
		productUpdate.setSku(sku);

		if (!this.products.contains(productUpdate)) {
			throw new ProductNotFound();
		}

		this.products.set(this.products.indexOf(productUpdate), product);
		return product;
	}

	public void remove(Long sku) throws ProductNotFound {
		Product productSaved = this.findById(sku);

		if (productSaved != null) {
			this.products.remove(productSaved);
		}
	}

}
