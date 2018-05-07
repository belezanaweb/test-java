package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blz.testjava.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
