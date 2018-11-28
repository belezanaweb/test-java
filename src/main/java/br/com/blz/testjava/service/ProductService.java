package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;

/**
 * Interface responsible for defining the methods of the Product service class. 
 * In it is validation of business rules.
 * 
 * @author Ramon Santos
 *
 */
public interface ProductService extends GenericService<Product> {

	Product findBySku(long sku);

	void editBySku(Product dto);

	void deleteBySku(long sku);

}
