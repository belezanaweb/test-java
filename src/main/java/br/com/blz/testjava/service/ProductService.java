package br.com.blz.testjava.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.entity.ProductEntity;
import br.com.blz.testjava.execption.ProductAlreadyExistException;
import br.com.blz.testjava.execption.ProductNotFoundException;
import br.com.blz.testjava.execption.SKUCoudNotBeChangedException;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<ProductDTO> findAll() {
		List<ProductEntity> productEntities = productRepository.findAll();

		return productEntities.stream().map(ProductEntity::toDTO).collect(Collectors.toList());
	}

	public Optional<ProductDTO> findBySku(Long sku) {
		return productRepository.findBySku(sku)
					.stream().filter(p -> p !=null)
					.findFirst().map(
							ProductEntity::toDTO
						);
	}

	public void save(ProductDTO product) throws ProductAlreadyExistException {
		if (productRepository.findBySku(product.getSku()).isEmpty()) {
			productRepository.save(product.toEntity());
		} else {
			throw new ProductAlreadyExistException("SKU aready exist");
		}
	}
	
	public void update(Long sku, ProductDTO product) throws SKUCoudNotBeChangedException, ProductNotFoundException {
		
		if (!sku.equals(product.getSku())) {
			throw new SKUCoudNotBeChangedException("SKU coud not be changed");
		} else if (productRepository.findBySku(sku).isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		}
		
		productRepository.save(product.toEntity());
	}
	
	public boolean delete(Long sku) {
		return productRepository.delete(sku);
	}
}
