package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.entity.Inventory;

@Repository
public interface IInventoryRepository extends JpaRepository<Inventory, Long> {

}