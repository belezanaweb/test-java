package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.DuplicateProductException;
import br.com.blz.testjava.exception.ProductNotFoundException;

public interface ProductService {
	void save(Product product) throws DuplicateProductException;

	void updateBySku(final Integer sku, final Product product) throws ProductNotFoundException;

	Product findBySku(Integer sku) throws ProductNotFoundException;

	void delete(Integer sku) throws ProductNotFoundException;

}
