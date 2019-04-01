package br.com.blz.testjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>{
	
	List<Warehouse> findByProductSku(Long sku);

}
