package br.com.blz.testjava.model.repositories;

import br.com.blz.testjava.model.entities.Warehouse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class WarehouseRepositoryTest {

    @Autowired
    WarehouseRepository repository;

    @Test
    @DisplayName("Deve encontrar um warehoyse por SKU e Locality")
    public void findByProductSkuAndNameLocalityTest() {
        Optional<Warehouse> foundWarehouse = repository.findByProductSkuAndNameLocality(43264L,"SP");
        assertThat(foundWarehouse.isPresent()).isTrue();
    }
}
