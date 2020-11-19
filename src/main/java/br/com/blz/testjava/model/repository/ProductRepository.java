package br.com.blz.testjava.model.repository;

import br.com.blz.testjava.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
