package br.com.blz.testjava.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.testjava.entity.InventoryWarehouse;

public interface InventoryWarehouseRepository extends CrudRepository<InventoryWarehouse, Integer> {

}
