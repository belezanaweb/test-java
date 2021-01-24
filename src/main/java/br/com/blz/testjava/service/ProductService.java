package br.com.blz.testjava.service;

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
		
		logger.info(String.format("findBySku %s", sku));
		
        Product product = productRepository.findBySku(sku);
        //calculateQuantity(product);

        return product;

	}
	
	public Product create(Product resquestProduct) throws ProductException {
		
		logger.info(String.format("create %s", resquestProduct));

        productRepository.save(resquestProduct);
        
        return resquestProduct;
	}
}
