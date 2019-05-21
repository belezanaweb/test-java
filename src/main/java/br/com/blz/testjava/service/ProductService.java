package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.dto.ProductsDTO;

public interface ProductService extends GenericService<Product, Long>{
	
	Product saveProduct(ProductsDTO product);
	
	Product findBySku(Long sku);

}
