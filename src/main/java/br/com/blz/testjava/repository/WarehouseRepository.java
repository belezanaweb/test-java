package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.Warehouse;
import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepository extends CrudRepository<Warehouse, Integer> {
}
