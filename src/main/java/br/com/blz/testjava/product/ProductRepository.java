package br.com.blz.testjava.product;

public interface ProductRepository{

	Product save(Product product);
	
	Product update(Product product);
	
	Product findBySku(Long sku);
	
	void delete(Long sku);
}
