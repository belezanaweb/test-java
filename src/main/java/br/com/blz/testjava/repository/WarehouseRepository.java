package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.*;

import br.com.blz.testjava.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, String>{

}
