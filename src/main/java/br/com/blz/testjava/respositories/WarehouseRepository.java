package br.com.blz.testjava.respositories;

import br.com.blz.testjava.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, String> {
    
}
