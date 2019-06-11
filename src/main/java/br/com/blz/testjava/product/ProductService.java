package br.com.blz.testjava.product;

public interface ProductService {
	
	Product saveProduct(Product product);
	
	Product getBySku(Long sku);
	
	Product update(Product product);
	
	void deleteBySku(Long sku);

}
