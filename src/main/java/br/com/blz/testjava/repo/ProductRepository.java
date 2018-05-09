package br.com.blz.testjava.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findBySku(int sku);
}
