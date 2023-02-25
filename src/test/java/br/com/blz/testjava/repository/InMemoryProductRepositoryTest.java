package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class InMemoryProductRepositoryTest {

    @Test
    public void saveProduct() {
        InMemoryProductRepository repository = initRepositoryMock();

        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = 43264L;

        Long totalProductQuantity = spWarehouseQuantity + moemaWarehouseQuantity;

        Product product = createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        repository.save(product);
        Optional<Product> persisted = repository.findBySku(sku);

        assertEquals(1, repository.count());
        assertTrue(persisted.isPresent());
        assertEquals(totalProductQuantity, persisted.get().getInventory().getQuantity());
    }

    @Test
    public void shouldNotSaveDuplicatedProducts() {
        InMemoryProductRepository repository = initRepositoryMock();

        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = 43264L;

        Product product = createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        repository.save(product);
        repository.save(product);

        assertEquals(1, repository.count());
    }

    @Test
    public void deleteProduct() {
        InMemoryProductRepository repository = initRepositoryMock();

        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = 43264L;

        Long totalProductQuantity = spWarehouseQuantity + moemaWarehouseQuantity;

        Product product = createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        repository.save(product);
        Optional<Product> persisted = repository.findBySku(sku);

        assertEquals(1, repository.count());
        assertTrue(persisted.isPresent());
        assertEquals(sku, persisted.get().getSku());

        repository.deleteBySku(sku);
        Optional<Product> deletedProduct = repository.findBySku(sku);

        assertEquals(0, repository.count());
        assertFalse(deletedProduct.isPresent());
    }

    @Test
    public void shouldReturnFalseWhenGivenSkuIsNotFound() {
        InMemoryProductRepository repository = initRepositoryMock();

        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = 43264L;

        Product product = createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        repository.save(product);
        Optional<Product> persisted = repository.findBySku(0L);

        assertEquals(1, repository.count());
        assertFalse(persisted.isPresent());
    }

    @Test
    public void updateProduct() {
        InMemoryProductRepository repository = initRepositoryMock();
        Long sku = 43264L;

        Long initialSpWarehouseQuantity = 12L;
        Long initialMoemaWarehouseQuantity = 3L;
        Long totalProductQuantity = initialSpWarehouseQuantity + initialMoemaWarehouseQuantity;

        Product product = createProductMock(
            initialSpWarehouseQuantity,
            initialMoemaWarehouseQuantity,
            sku
        );

        repository.save(product);

        Optional<Product> persisted = repository.findBySku(sku);

        // asserting initial product state
        assertEquals(1, repository.count());
        assertTrue(persisted.isPresent());
        assertEquals(sku, persisted.get().getSku());
        assertEquals(totalProductQuantity, persisted.get().getInventory().getQuantity());

        // updating product
        Product productToUpdate = persisted.get();
        productToUpdate.setName("Updated Product Name");

        Long updatedSpWarehouseQuantity = 20L;
        Long updatedMoemaWarehouseQuantity = 10L;
        Long updatedTotalProductQuantity = updatedSpWarehouseQuantity + updatedMoemaWarehouseQuantity;

        Warehouse spWarehouse = new Warehouse(
            "SP",
            updatedSpWarehouseQuantity,
            WarehouseType.ECOMMERCE
        );

        Warehouse moemaWarehouse = new Warehouse(
            "MOEMA",
            updatedMoemaWarehouseQuantity,
            WarehouseType.PHYSICAL_STORE
        );

        List<Warehouse> warehouses = new ArrayList<>(2);
        warehouses.add(spWarehouse);
        warehouses.add(moemaWarehouse);

        Inventory updatedInventory = new Inventory(warehouses);
        productToUpdate.setInventory(updatedInventory);

        repository.update(productToUpdate);

        Optional<Product> persistedAfterUpdate = repository.findBySku(sku);

        // asserting updated product
        assertEquals(1, repository.count());
        assertEquals(sku, persistedAfterUpdate.get().getSku());
        assertEquals("Updated Product Name", persistedAfterUpdate.get().getName());
        assertEquals(updatedTotalProductQuantity, persistedAfterUpdate.get().getInventory().getQuantity());

    }

    private InMemoryProductRepository initRepositoryMock(){
        List<Product> products = new ArrayList<>();
        return new InMemoryProductRepository(products);
    }

    private Product createProductMock(Long spWarehouseQuantity, Long moemaWarehouseQuantity, Long sku) {
        Warehouse spWarehouse = new Warehouse("SP", spWarehouseQuantity, WarehouseType.ECOMMERCE);
        Warehouse moemaWarehouse = new Warehouse("MOEMA", moemaWarehouseQuantity, WarehouseType.PHYSICAL_STORE);

        List<Warehouse> warehouses = new ArrayList<>(2);
        warehouses.add(spWarehouse);
        warehouses.add(moemaWarehouse);

        Inventory inventory = new Inventory(warehouses);

        return new Product(sku, "Product", inventory);
    }

}
