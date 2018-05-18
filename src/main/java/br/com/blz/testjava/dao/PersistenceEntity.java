package br.com.blz.testjava.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.blz.testjava.model.Product;

public final class PersistenceEntity {
	private final Map<Integer, Product> savedProducts;
	private static final PersistenceEntity INSTANCE = new PersistenceEntity();

	private PersistenceEntity() {
		this.savedProducts = new HashMap<Integer, Product>();
	}

	public static PersistenceEntity getInstance() {
		return INSTANCE;
	}

	public Optional<Product> findBySku(final Integer sku) {
		return Optional.ofNullable(savedProducts.get(sku));
	}

	public Map<Integer, Product> findAll() {
		return savedProducts;
	}

	public Product delete(final Integer sku) {
		return savedProducts.remove(sku);
	}
}
