package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.Warehouse;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

    List<Warehouse> findByProductSku(Long sku);
    
    @Transactional
    void deleteByProductSku(Long sku);
}
