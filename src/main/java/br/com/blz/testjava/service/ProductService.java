package br.com.blz.testjava.service;

import br.com.blz.testjava.exceptions.ProductAlreadyExistsException;
import br.com.blz.testjava.model.Message;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.util.ResponseDefault;

/**
 * Interface de serviço de produto
 * 
 * @author ledzo
 *
 */
public interface ProductService {
	
	ResponseDefault<Message> createProduct(Product product) throws ProductAlreadyExistsException;
	ResponseDefault<Message> alterProduct(Long sku, Product product);
	ResponseDefault<Message> deleteProduct(Long sku);
	ResponseDefault<Product> findProduct(Long sku);
	
}
