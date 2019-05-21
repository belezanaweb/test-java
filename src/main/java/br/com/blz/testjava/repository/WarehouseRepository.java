package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    void deleteAllByProduct(Product product);

    List<Warehouse> findByProduct(Product product);
}


