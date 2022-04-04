package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.Product;

public interface IProductRepository {
	boolean existsBySku(final Long sku);
	Product findOne(final Long sku);
	Product create(final Product newProduct);
	void delete(final Long sku);
	Product update(final Long sku, final Product newProduct);
	void deleteAll();
}
