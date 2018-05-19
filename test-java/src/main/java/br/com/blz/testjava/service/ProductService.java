package br.com.blz.testjava.service;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.ProductAlreadyRegisteredException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Integer saveProduct(Product product) throws ProductAlreadyRegisteredException {
		checkNotNull(product);
		return productRepository.save(new Product(product.getSku(), product.getName(), product.getInventory(), product.isMarketable()));
	}

	public Integer updateProduct(Integer sku, Product product) throws ProductNotFoundException {
		checkNotNull(product);
		return productRepository.update(sku, product);
	}

	public Integer deleteProduct(Integer sku) throws ProductNotFoundException {
		return productRepository.delete(sku);
	}
	
	public Product findProductBySku(Integer sku) throws ProductNotFoundException {
		Product product = productRepository.findBySku(sku);
		calculations(sku, checkNotNull(product));
		return product;
	}
	
	private void calculations(Integer sku, Product product) throws ProductNotFoundException{
		calculateQuantity(product);
		calculateIfIsMarketable(product);
		productRepository.update(sku, product);
	}

	private void calculateQuantity(Product product) {
		int sum = 0;
		Inventory inventory = product.getInventory();

		for (Warehouse warehouse : inventory.getWarehouses()) {
			sum += warehouse.getQuantity();
		}

		inventory.setQuantity(sum);
	}
	
	private void calculateIfIsMarketable(Product product ) {
		product.setMarketable(product.getInventory().getQuantity() > 0 ? true : false);
	}
}
