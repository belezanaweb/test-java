package br.com.blz.service;

import br.com.blz.domain.Product;

public interface ProductService extends GenericService<Product> {

	Product findBySku(long sku) throws Exception;

	void updateBySku(Product dto) throws Exception;

	void deleteBySku(long sku) throws Exception;

}
