package br.com.blz.testjava.persistence.repository;

import br.com.blz.testjava.persistence.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductInventory, Long> {
    List<ProductInventory> findBySku(Long sku);
}
