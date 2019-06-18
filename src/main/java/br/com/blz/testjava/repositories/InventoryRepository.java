package br.com.blz.testjava.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.models.Inventory;
 
@Repository	
public interface InventoryRepository extends CrudRepository<Inventory, Long>{
}
