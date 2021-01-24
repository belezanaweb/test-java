package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Product;

@Repository ("ProductRepository")
public class InMemoryProductRepository implements IProductRepository {

	private final Map<Long, Product> inMemoryDb = new HashMap<>();

	public Product save(final Product product) {
		inMemoryDb.put(product.getSku(), product);
		return product;
	}
	
	public Optional<Product> findById(final Long sku) {
		return Optional.ofNullable(inMemoryDb.get(sku));
	}

	public boolean existsById(final Long sku) {
		return inMemoryDb.containsKey(sku);
	}

	public void deleteById(final Long sku) {
		inMemoryDb.remove(sku);
	}

	

}
