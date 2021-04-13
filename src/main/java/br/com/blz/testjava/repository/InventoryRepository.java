package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.entity.Inventory;
import dto.InventoryDTO;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
