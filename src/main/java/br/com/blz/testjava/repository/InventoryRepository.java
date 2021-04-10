package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.*;

import br.com.blz.testjava.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, String>{

}
