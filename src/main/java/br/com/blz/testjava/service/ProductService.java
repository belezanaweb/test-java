package br.com.blz.testjava.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dao.entity.Product;
import br.com.blz.testjava.dao.repository.IProductRepository;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.exception.ProductSKUMismatchException;

@Service
public class ProductService implements IProductService{

	private static final String PRODUCT_ALREADY_EXIST = "Product Already Exist.";
	private static final String PRODUCT_SKU_MISMATCH = "Product SKU mismatch.";
	@Autowired
    private IProductRepository productRepository;
	
	@Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
    
	@Override
	public Product findById(Long sku) {
		return productRepository.findById(sku).orElseThrow(ProductNotFoundException::new);
	}

	@Override
    public Product create(Product product) {
    	if (productRepository.findById(product.getSku()).isPresent()) {
            throw new ProductAlreadyExistException(PRODUCT_ALREADY_EXIST);
    	}
    	return productRepository.save(product);
    }

	@Override
    public Product update(Long sku, Product product) {
    	if (!product.getSku().equals(sku)) {
             throw new ProductSKUMismatchException(PRODUCT_SKU_MISMATCH);
    	}
    	Product productStored = findById(sku);
		BeanUtils.copyProperties(product, productStored, "sku");
		return productRepository.save(product);
    }

	@Override
    public void delete(Long sku) {
		findById(sku);
        productRepository.deleteById(sku);
    }   
}