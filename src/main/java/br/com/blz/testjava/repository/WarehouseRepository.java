package br.com.blz.testjava.repository;


import br.com.blz.testjava.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends CrudRepository<WarehouseEntity, Long> {

    @Query("SELECT new WarehouseEntity(w.id, w.locality, w.quantity, w.type) FROM WarehouseEntity w WHERE w.inventoryEntity.id = :id")
    List<WarehouseEntity> findByInventory(@Param("id") Long id);

}
