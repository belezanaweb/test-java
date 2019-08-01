package br.com.blz.testjava.repository;

import br.com.blz.testjava.object.Warehouses;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepository extends CrudRepository<Warehouses, Long> {

    List<Warehouses> findByProductSku(Long sku);

    @Transactional
    void deleteByProductSku(Long sku);
}