package br.com.blz.testjava.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.ProductException;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
	
    Logger logger = LoggerFactory.getLogger(ProductService.class);
    
	public Product findBySku(Long sku) throws ProductException {

		logger.info(String.format("INI :: findBySku %s", sku));
		
        Product product = productRepository.findBySku(sku);
        
		logger.info(String.format("FIM :: findBySku %s", sku));

        return product;

	}
	
	public Product create(Product resquestProduct) throws ProductException {
		
		logger.info(String.format("INI :: create %s", resquestProduct));

		Product newProduct = new Product(null, null, null);
		newProduct.setSku(resquestProduct.getSku());
		newProduct.setName(resquestProduct.getName());
		newProduct.setInventory(resquestProduct.getInventory());
		
        productRepository.save(resquestProduct);
		
		logger.info(String.format("FIM :: create %s", resquestProduct));
        
        return resquestProduct;
	}
	
	public Product update(Long sku, Product resquestProduct) throws ProductException {

		logger.info(String.format("INI :: update %s", resquestProduct));

		Product oldProduct = productRepository.findBySku(sku);

        //if(oldProduct.getSku() != sku) {
		//	throw new ProductException("Produto encontrado com sku diferente.");
        //}
        
		Product newProduct = oldProduct;
		newProduct.setName(resquestProduct.getName());
		newProduct.setInventory(resquestProduct.getInventory());
		
        Product product = productRepository.update(newProduct);
		
		logger.info(String.format("FIM :: update %s", resquestProduct));

        return findBySku(product.getSku());
    }

	public void delete(Long sku) throws ProductException {

		logger.info(String.format("INI :: delete %s", sku));

		productRepository.delete(sku);

		logger.info(String.format("INI :: delete %s", sku));
	}
}
