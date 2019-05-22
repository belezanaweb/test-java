package br.com.blz.testjava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blz.testjava.model.Product;

public interface Products extends JpaRepository<Product, Long> {

	Optional<Product> findBySku(Integer sku);

}
