package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dao.entity.Product;
import br.com.blz.testjava.dao.entity.ProductEntryPK;
import br.com.blz.testjava.dao.repository.IProductRepository;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.exception.ProductSKUMismatchException;

@Service
public class ProductService implements IProductService{

	@Autowired
    private IProductRepository productRepository;
	
	@Override
    public Iterable<Product> findAll() {
        Iterable<Product> products = productRepository.findAll();
        return products;
    }
    
	@Override
    public Product findById(Long sku) {
    	return productRepository.findById(buildProdSku(sku))
    			.orElseThrow(ProductNotFoundException::new);
    }

	@Override
    public Product create(Product product) {
    	if (null == product.getSku()){
            throw new IllegalArgumentException("Sku is requerid.");
    	}
    	
    	if (productRepository.findById(product.getSku()).isPresent()) {
            throw new IllegalArgumentException("Product Already Exists");
    	}
        return productRepository.save(product);
    }

	public static ProductEntryPK buildProdSku(Long sku) {
		ProductEntryPK productSku = new ProductEntryPK();
    	productSku.setSku(sku);
    	return productSku;
	}
    
	@Override
    public Product update(Long sku, Product product) {
    	if (null == product.getSku() || !product.getSku().getSku().equals(sku)) {
             throw new ProductSKUMismatchException("Product SKU mismatch.");
    	}
    	productRepository.findById(buildProdSku(sku))
        .orElseThrow(ProductNotFoundException::new);
    	
        return productRepository.save(product);
    }

	@Override
    public void delete(Long sku) {
    	 productRepository.findById(buildProdSku(sku))
         .orElseThrow(ProductNotFoundException::new);
       productRepository.deleteById(buildProdSku(sku));
    }   
}