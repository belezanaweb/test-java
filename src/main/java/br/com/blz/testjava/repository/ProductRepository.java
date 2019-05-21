package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blz.testjava.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product findBySku(Long Sku);

}
