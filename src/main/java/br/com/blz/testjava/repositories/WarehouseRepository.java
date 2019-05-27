package br.com.blz.testjava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.models.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
	
	public List<Warehouse> findByInventoryId(@Param("inventoryId") Long inventoryId);

}
