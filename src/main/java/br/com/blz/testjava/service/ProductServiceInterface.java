package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;

public interface ProductServiceInterface {
	
	public void persistProduct(Product product);
	
	public Boolean updateProduct(Long productSku, Product product);
	
	public Product findProduct(Long productSku);
	
	public Boolean deleteProduct(Long productSku);

}
