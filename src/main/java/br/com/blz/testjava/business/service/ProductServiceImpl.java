package br.com.blz.testjava.business.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.business.domain.GlobalMessage;
import br.com.blz.testjava.business.domain.Product;
import br.com.blz.testjava.business.repository.ProductRepository;

import br.com.blz.testjava.common.exception.BusinessException;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product save(Product product) {
		if (productAlreadyExist(product)) {
			throw new BusinessException(GlobalMessage.PRODUCT_DUPLICATED);
		}
		
		return productRepository.save(product);
	}
	
	@Override
	public Product update(Product product, Long sku) {
		if (!product.getSku().equals(sku)) {
			throw new BusinessException(GlobalMessage.DIFFERENT_IDS);
		}
		
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> findBySku(Long sku) {
		Optional<Product> foundProductOptional = productRepository.findById(sku);
		
		if (!foundProductOptional.isPresent()) {
			return foundProductOptional;
		}
		
		Product currentProduct = foundProductOptional.get();
		
		int quantity = Optional.ofNullable(currentProduct.getInventory().getWarehouses())
				.orElse(Collections.emptyList())
				.stream()
				.reduce(0, (quantityResult, prod) -> quantityResult + prod.getQuantity(), Integer::sum);
		
		currentProduct.getInventory().setQuantity(quantity);
		
		if (quantity > 0) {
			currentProduct.setMarketable(true);
		}
		
		return Optional.of(currentProduct);
	}
	
	@Override
	public void deleteBySku(Long sku) {
		this.productRepository.deleteById(sku);
	}
	
	private boolean productAlreadyExist(Product product) {
		return productRepository.findById(product.getSku()).isPresent();
	}
}
