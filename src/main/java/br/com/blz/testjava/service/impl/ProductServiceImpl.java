package br.com.blz.testjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.DuplicateProductException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void save(final Product product) throws DuplicateProductException{
		if(saveProduct(product.getSku())) {
			productRepository.save(product);
		}else {
			throw new DuplicateProductException();
		}
	}

	@Override
	public void updateBySku(final Integer sku, final Product product) throws ProductNotFoundException {
		if(existsProduct(sku)) {
			productRepository.update(product);
		}else {
			throw new ProductNotFoundException();
		}
	}

	@Override
	public Product findBySku(final Integer sku) throws ProductNotFoundException {
		Product product = null;
		
		if(existsProduct(sku)) {
			product = productRepository.findBySku(sku);
			calculateTotalQuantityInventory(product);
			defineMarketableInProduct(product);	
		}else {
			throw new ProductNotFoundException();
		}
		
		return product;
	}

	@Override
	public void delete(final Integer sku) throws ProductNotFoundException{
		if(deleteProduct(sku)) {
			productRepository.delete(sku);	
		}else {
			throw new ProductNotFoundException();
		}
	}
	
	private boolean saveProduct(final Integer sku) {
		return !existsProduct(sku);
	}
	
	private boolean existsProduct(final Integer sku) {
		return productRepository.existsProduct(sku);
	}
	
	private boolean deleteProduct(final Integer sku) {
		return existsProduct(sku);
	}
	
	private void calculateTotalQuantityInventory(final Product product) {
		Integer quantityInventory = 0;
		
		for(Warehouse warehouse : product.getInventory().getWarehouses()) {
			quantityInventory += warehouse.getQuantity();
		}
		
		product.getInventory().setQuantity(quantityInventory);
	}
	
	private void defineMarketableInProduct(final Product product) {
		product.setMarketable(product.getInventory().getQuantity() > 0);
	}
	
}
