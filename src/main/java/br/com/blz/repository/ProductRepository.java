package br.com.blz.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	Optional<Product> findBySku(long sku);
	
}
