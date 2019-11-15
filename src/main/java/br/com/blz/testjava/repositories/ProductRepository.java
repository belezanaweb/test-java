package br.com.blz.testjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blz.testjava.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsBySku(Long sku);

	Product findBySku(Long sku);

	Product findAllProducts(Long sku);

	Product findById(long sku);

}
