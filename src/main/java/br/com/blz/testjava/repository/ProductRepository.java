package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.domain.Product;

@Repository
public class ProductRepository implements IProductRepository {
	private static final Map<Long, Product> PRODUCTS_DATABASE = new HashMap<>(); 
	
	@Override
	public boolean existsBySku(final Long sku) {
		return PRODUCTS_DATABASE.containsKey(sku);
	}
	
	@Override
	public Product findOne(final Long sku) {
		return PRODUCTS_DATABASE.get(sku);
	}

	@Override
	public Product create(final Product newProduct) {
		PRODUCTS_DATABASE.put(newProduct.getSku(), newProduct);
		return newProduct;
	}

	@Override
	public void delete(final Long sku) {
		PRODUCTS_DATABASE.remove(sku);
	}

	@Override
	public Product update(final Long sku, final Product newProduct) {
		PRODUCTS_DATABASE.replace(sku, newProduct);
		return newProduct;
	}

	@Override
	public void deleteAll() {
		PRODUCTS_DATABASE.clear();
	}
}