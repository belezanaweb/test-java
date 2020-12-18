package br.com.blz.testjava.model.repositories;

import br.com.blz.testjava.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(" select p from Product p" +
           " join fetch p.inventory v" +
           " join fetch v.locality l" +
           " where p.sku = :sku")
    Optional<Product> findBySku(@Param("sku") Long sku);
}
