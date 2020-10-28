package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
