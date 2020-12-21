package br.com.blz.testjava.persistence.repository;

import br.com.blz.testjava.persistence.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
