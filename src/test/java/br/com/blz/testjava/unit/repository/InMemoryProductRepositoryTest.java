package br.com.blz.testjava.unit.repository;

import br.com.blz.testjava.exceptions.DuplicatedProductException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseType;
import br.com.blz.testjava.repository.InMemoryProductRepository;
import br.com.blz.testjava.share.ProductMock;
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
        Long sku = ProductMock.getRandomSku();

        Long totalProductQuantity = spWarehouseQuantity + moemaWarehouseQuantity;

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        repository.save(product);
        Optional<Product> persisted = repository.findBySku(sku);

        assertEquals(1, repository.count());
        assertTrue(persisted.isPresent());
        assertEquals(totalProductQuantity, persisted.get().getInventory().getQuantity());
        assertTrue(persisted.get().getIsMarketable());
    }

    @Test
    public void assertThatProductsWithInventoryQuantityEqualsZeroAreNotMarketable() {
        InMemoryProductRepository repository = initRepositoryMock();

        Long spWarehouseQuantity = 0L;
        Long moemaWarehouseQuantity = 0L;
        Long totalProductQuantity = 0L;
        Long sku = ProductMock.getRandomSku();

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        repository.save(product);
        Optional<Product> persisted = repository.findBySku(sku);

        assertEquals(1, repository.count());
        assertTrue(persisted.isPresent());
        assertEquals(totalProductQuantity, persisted.get().getInventory().getQuantity());
        assertFalse(persisted.get().getIsMarketable());
    }

    @Test
    public void shouldThrowDuplicatedProductExceptionWhenSaveDuplicatedProducts() {
        InMemoryProductRepository repository = initRepositoryMock();

        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = ProductMock.getRandomSku();

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        repository.save(product);

        Exception exception = assertThrows(DuplicatedProductException.class, () -> repository.save(product));

        assertEquals("Duplicated SKU", exception.getMessage());
    }

    @Test
    public void deleteProduct() {
        InMemoryProductRepository repository = initRepositoryMock();

        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = ProductMock.getRandomSku();

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

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
        Long sku = ProductMock.getRandomSku();

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        repository.save(product);
        Optional<Product> persisted = repository.findBySku(0L);

        assertEquals(1, repository.count());
        assertFalse(persisted.isPresent());
    }

    @Test
    public void updateProduct() {
        InMemoryProductRepository repository = initRepositoryMock();
        Long sku = ProductMock.getRandomSku();

        Long initialSpWarehouseQuantity = 12L;
        Long initialMoemaWarehouseQuantity = 3L;
        Long totalProductQuantity = initialSpWarehouseQuantity + initialMoemaWarehouseQuantity;

        Product product = ProductMock.createProductMock(
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
        assertTrue(persistedAfterUpdate.isPresent());
        assertEquals(sku, persistedAfterUpdate.get().getSku());
        assertEquals("Updated Product Name", persistedAfterUpdate.get().getName());
        assertEquals(updatedTotalProductQuantity, persistedAfterUpdate.get().getInventory().getQuantity());

    }

    private InMemoryProductRepository initRepositoryMock(){
        List<Product> products = new ArrayList<>();
        return new InMemoryProductRepository(products);
    }

}
