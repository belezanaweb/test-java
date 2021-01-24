package br.com.blz.testjava.repository;

import java.util.Optional;

import br.com.blz.testjava.model.Product;

public interface IProductRepository {
	public Product save(final Product product);

	public Optional<Product> findById(final Long sku);

	public boolean existsById(final Long sku);

	public void deleteById(final Long sku);

}
