package br.com.blz.testjava.repository;

import br.com.blz.testjava.enums.BrazilianStates;
import br.com.blz.testjava.enums.WarehouseType;
import br.com.blz.testjava.persistence.entity.Warehouse;
import br.com.blz.testjava.persistence.repository.ProductRepository;
import br.com.blz.testjava.persistence.repository.WarehouseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Transactional
@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
@Rollback
public class WarehouseRepositoryTest {

    @Autowired
    private WarehouseRepository fixture;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Rollback
    public void testInsert() {

        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = fixture.saveAndFlush(warehouse);

        assertThat(warehouse).isNotNull();
        assertThat(warehouse.getWarehouseID()).isNotNull();

        Optional<Warehouse> entityOptional = fixture.findById(warehouse.getWarehouseID());
        assertThat(entityOptional).isNotEmpty();
    }

    @Test
    @Rollback
    public void testUpdate() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = fixture.saveAndFlush(warehouse);

        warehouse.setCreatedBy("Other");

        Warehouse updatedEntity = fixture.saveAndFlush(warehouse);
        assertThat(updatedEntity).isNotNull();
        assertThat(updatedEntity.getWarehouseID()).isNotNull();
        assertThat(updatedEntity.getWarehouseID()).isEqualTo(warehouse.getWarehouseID());
    }

    @Test
    @Rollback
    public void testDelete() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = fixture.saveAndFlush(warehouse);

        fixture.delete(warehouse);

        Optional<Warehouse> entityOptional = fixture.findById(warehouse.getWarehouseID());
        assertThat(entityOptional).isEmpty();
    }

    @Test
    @Rollback
    public void testSelect() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = fixture.saveAndFlush(warehouse);

        Optional<Warehouse> result = fixture.findById(warehouse.getWarehouseID());
        assertThat(result).isNotNull();
        assertThat(result).contains(warehouse);
    }

    @Test
    @Rollback
    public void testSelectAll() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = fixture.saveAndFlush(warehouse);

        List<Warehouse> entities = fixture.findAll();
        assertThat(entities).isNotEmpty();
        assertThat(entities).contains(warehouse);
    }


    @Test
    public void testEnumConverter() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = fixture.saveAndFlush(warehouse);

        Warehouse dbEntity = fixture.findById(warehouse.getWarehouseID()).orElse(null);

        assertThat(dbEntity).isNotNull();
        assertThat(dbEntity.getType()).isEqualTo(warehouse.getType());
        assertThat(dbEntity.getLocality()).isEqualTo(warehouse.getLocality());
    }
}
