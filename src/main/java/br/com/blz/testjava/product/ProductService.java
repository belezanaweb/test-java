package br.com.blz.testjava.product;

import br.com.blz.testjava.error.NoProductResultException;
import br.com.blz.testjava.error.ProductSavedException;

public interface ProductService {
	
	Product saveProduct(Product product) throws ProductSavedException;
	
	Product getBySku(Long sku);
	
	Product update(Product product) throws NoProductResultException;
	
	void deleteBySku(Long sku) throws NoProductResultException;

}
