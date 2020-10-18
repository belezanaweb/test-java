package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blz.testjava.model.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long>{

}
