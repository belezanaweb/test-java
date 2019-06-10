package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.entity.Warehouse;

@Repository
public interface IWarehouseRepository extends JpaRepository<Warehouse, Long> {

}