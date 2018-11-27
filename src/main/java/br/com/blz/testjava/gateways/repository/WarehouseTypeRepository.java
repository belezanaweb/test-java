package br.com.blz.testjava.gateways.repository;

import br.com.blz.testjava.domains.WarehouseTypeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseTypeRepository extends JpaRepository<WarehouseTypeEntity, Integer> {

    Optional<WarehouseTypeEntity> findByDescription(String description);
}
