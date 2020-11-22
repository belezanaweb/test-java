package br.com.blz.testjava.model.repository;

import br.com.blz.testjava.model.entities.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  boolean existsBySku(Long sku);

  Optional<Product> findBySku(Long sku);
}
