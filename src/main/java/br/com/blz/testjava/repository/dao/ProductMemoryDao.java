package br.com.blz.testjava.repository.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.repository.entity.Product;

@Repository
public class ProductMemoryDao implements ProductRepository {

	private Set<Product> products = new HashSet<>();

	public Product insert(Product product) {
		products.add(product);
		return product;
	}

	public Product update(Product product) {
		products.add(product);
		return product;
	}

	public Product findById(long sku) {
		return products.parallelStream()
				.filter(p -> p.getSku() == sku)
				.findFirst()
				.orElse(null);
	}

	public boolean delete(long sku) {
		Product product = findById(sku);
		return products.remove(product);
	}

	public List<Product> findAll() {
		return new ArrayList<>(products);
	}

	public void removeAll() {
		products.clear();
	}

}
