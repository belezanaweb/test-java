package br.com.blz.testjava.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.testjava.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

}
