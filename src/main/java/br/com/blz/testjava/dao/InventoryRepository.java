package br.com.blz.testjava.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.testjava.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}
