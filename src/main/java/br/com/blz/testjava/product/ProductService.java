package br.com.blz.testjava.product;

public interface ProductService {

	Product findBySku(Long sku);
	
	Product save(Product post);
	
	void delete(Long id);
}
