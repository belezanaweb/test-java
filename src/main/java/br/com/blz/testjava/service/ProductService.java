package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;

public interface ProductService {

	Integer saveOrUpdate(final Product product);

	Product findBySku(final Integer sku);

	Product delete(final Integer sku);
}
