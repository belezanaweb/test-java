package br.com.blz.testjava.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.testjava.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	Optional<Product> findBySku(long sku);
}
