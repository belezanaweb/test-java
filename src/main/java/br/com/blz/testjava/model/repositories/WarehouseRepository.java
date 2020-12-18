package br.com.blz.testjava.model.repositories;

import br.com.blz.testjava.model.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query( " select w from Warehouse w"
          + " where w.locality.name = :nameLocality"
          + " and w.product.sku = :sku")
    Optional<Warehouse> findByProductSkuAndNameLocality(@Param("sku")Long sku, @Param("nameLocality") String nameLocality);
}
