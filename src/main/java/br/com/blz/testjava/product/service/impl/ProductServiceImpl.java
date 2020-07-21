package br.com.blz.testjava.product.service.impl;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.product.model.Product;
import br.com.blz.testjava.product.repository.ProductRepository;
import br.com.blz.testjava.product.service.ProductService;
import br.com.blz.testjava.product.util.ProductException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	private ProductRepository repository;
	
	@Override
	@Transactional
	public void create(Product product) throws ProductException {
		try {
			repository.save(product);
		} catch (DataIntegrityViolationException e) {
			throw new ProductException("Product already exists!");
		}
	}

	@Override
	@Transactional
	public void delete(Long sku) {
		repository.deleteBySku(sku);
	}

	@Override
	@Transactional
	public Product update(Long sku, Product product) {
		Product original = repository.findBySku(sku);
		if(original == null) throw new ProductException("Product not found!");
		
		original.setInventory(product.getInventory());
		original.setName(product.getName());
		
		return repository.save(original);
	}

	@Override
	public Product find(Long sku) {
		return repository.findBySku(sku);
	}

}
