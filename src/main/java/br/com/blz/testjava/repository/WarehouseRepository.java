package br.com.blz.testjava.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.testjava.entity.Type;
import br.com.blz.testjava.entity.Warehouse;

public interface WarehouseRepository extends CrudRepository<Warehouse, Integer> {

	Warehouse findByLocalityAndType(String locality, Type type);

}
