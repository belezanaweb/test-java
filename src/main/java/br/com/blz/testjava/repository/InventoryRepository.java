package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {
}
