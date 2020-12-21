package br.com.blz.testjava.repository;

import br.com.blz.testjava.enums.BrazilianStates;
import br.com.blz.testjava.enums.WarehouseType;
import br.com.blz.testjava.persistence.entity.ProductInventory;
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
public class ProductInventoryRepositoryTest {

    @Autowired
    private ProductRepository fixture;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Test
    @Rollback
    public void testInsert() {

        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = warehouseRepository.saveAndFlush(warehouse);

        ProductInventory entity = new ProductInventory();
        entity.setSku(123L);
        entity.setWarehouse(warehouse);

        entity = fixture.saveAndFlush(entity);

        assertThat(entity).isNotNull();
        assertThat(entity.getProductId()).isNotNull();

        Optional<ProductInventory> entityOptional = fixture.findById(entity.getProductId());
        assertThat(entityOptional).isNotEmpty();
    }

    @Test
    @Rollback
    public void testUpdate() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = warehouseRepository.saveAndFlush(warehouse);

        ProductInventory entity = new ProductInventory();
        entity.setSku(123L);
        entity.setWarehouse(warehouse);

        entity = fixture.saveAndFlush(entity);

        entity.setCreatedBy("Other");

        ProductInventory updatedEntity = fixture.saveAndFlush(entity);
        assertThat(updatedEntity).isNotNull();
        assertThat(updatedEntity.getProductId()).isNotNull();
        assertThat(updatedEntity.getProductId()).isEqualTo(entity.getProductId());
    }

    @Test
    @Rollback
    public void testDelete() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = warehouseRepository.saveAndFlush(warehouse);

        ProductInventory entity = new ProductInventory();
        entity.setSku(123L);
        entity.setWarehouse(warehouse);

        entity = fixture.saveAndFlush(entity);

        fixture.delete(entity);

        Optional<ProductInventory> entityOptional = fixture.findById(entity.getProductId());
        assertThat(entityOptional).isEmpty();
    }

    @Test
    @Rollback
    public void testSelect() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = warehouseRepository.saveAndFlush(warehouse);

        ProductInventory entity = new ProductInventory();
        entity.setSku(123L);
        entity.setWarehouse(warehouse);

        entity = fixture.saveAndFlush(entity);

        Optional<ProductInventory> result = fixture.findById(entity.getProductId());
        assertThat(result).isNotNull();
        assertThat(result).contains(entity);
    }

    @Test
    @Rollback
    public void testSelectAll() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = warehouseRepository.saveAndFlush(warehouse);

        ProductInventory entity = new ProductInventory();
        entity.setSku(123L);
        entity.setWarehouse(warehouse);

        entity = fixture.saveAndFlush(entity);

        List<ProductInventory> entities = fixture.findAll();
        assertThat(entities).isNotEmpty();
        assertThat(entities).contains(entity);
    }

    @Test
    public void testRelationshipManyToOne() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = warehouseRepository.saveAndFlush(warehouse);

        ProductInventory entity = new ProductInventory();
        entity.setSku(123L);
        entity.setWarehouse(warehouse);

        entity = fixture.saveAndFlush(entity);

        assertThat(entity.getWarehouse()).isNotNull();
        assertThat(entity.getWarehouse().getWarehouseID()).isNotNull();
    }

    @Test
    public void testFindBySku() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(BrazilianStates.PERNAMBUCO);
        warehouse.setType(WarehouseType.PHYSICAL_STORE);

        warehouse = warehouseRepository.saveAndFlush(warehouse);

        ProductInventory entity = new ProductInventory();
        entity.setSku(123L);
        entity.setWarehouse(warehouse);

        entity = fixture.saveAndFlush(entity);

        List<ProductInventory> result = fixture.findBySku(123L);

        assertThat(result).isNotEmpty();
        assertThat(result).contains(entity);
    }

}
