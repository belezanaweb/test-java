package br.com.blz.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.ResponseDTO;
import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseDTO createProduct(Product product) throws ProductAlreadyExistsException {
		if (productRepository.insert(product)) {
			return new ResponseDTO("Product created");
		}
		throw new ProductAlreadyExistsException(product.getSku());
		
	}
	
	public Product getProductBySKU(Long sku) throws ProductNotFoundException {
		Product p = productRepository.getProductBySKU(sku);
		if (p != null) {
			return p;
		}
		throw new ProductNotFoundException(sku);
	}
	
	public ResponseDTO updateProduct(Long sku, Product product) throws ProductNotFoundException {
		if (productRepository.update(product)) {
			return new ResponseDTO("Product updated");
		}
		throw new ProductNotFoundException(sku);
		
	}
	
	public ResponseDTO deleteProduct(Long sku) throws ProductNotFoundException {
		if (productRepository.delete(sku)) {
			return new ResponseDTO("Product deleted");
		}
		throw new ProductNotFoundException(sku);
		
	}
	
	public List<Product> listAll(){
		return productRepository.listAll();
	}
	
	
	
	

}
