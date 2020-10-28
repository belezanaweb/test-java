package br.com.blz.testjava.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
