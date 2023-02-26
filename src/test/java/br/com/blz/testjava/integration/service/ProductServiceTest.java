package br.com.blz.testjava.integration.service;

import br.com.blz.testjava.TestJavaApplication;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseType;
import br.com.blz.testjava.repository.Repository;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.share.ProductMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestJavaApplication.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private Repository repository;

    @Test
    public void saveProduct() {
        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = 43264L;

        Long totalProductQuantity = spWarehouseQuantity + moemaWarehouseQuantity;

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        Mockito.when(repository.exists(product)).thenReturn(false);
        Mockito.when(repository.findBySku(sku)).thenReturn(Optional.of(product));
        Mockito.when(repository.count()).thenReturn(1);

        productService.save(product);

        Optional<Product> persisted = productService.findBySku(sku);

        assertEquals(1, productService.count());
        assertTrue(persisted.isPresent());
        assertEquals(totalProductQuantity, persisted.get().getInventory().getQuantity());
    }

    @Test
    public void deleteProduct() {
        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = 43264L;

        Long totalProductQuantity = spWarehouseQuantity + moemaWarehouseQuantity;

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        Mockito.when(repository.exists(product)).thenReturn(false);
        Mockito.when(repository.findBySku(sku)).thenReturn(Optional.of(product));
        Mockito.when(repository.count()).thenReturn(1);

        productService.save(product);

        Optional<Product> persisted = productService.findBySku(sku);

        assertEquals(1, productService.count());
        assertTrue(persisted.isPresent());
        assertEquals(totalProductQuantity, persisted.get().getInventory().getQuantity());

        productService.deleteBySku(sku);

        Mockito.when(repository.findBySku(sku)).thenReturn(Optional.empty());
        Mockito.when(repository.count()).thenReturn(0);

        Optional<Product> deletedProduct = productService.findBySku(sku);

        assertEquals(0, productService.count());
        assertFalse(deletedProduct.isPresent());
    }

    @Test
    public void shouldReturnFalseWhenGivenSkuIsNotFound() {
        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = 43264L;

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        Mockito.when(repository.exists(product)).thenReturn(false);
        Mockito.when(repository.findBySku(sku)).thenReturn(Optional.of(product));

        productService.save(product);

        Optional<Product> persisted = productService.findBySku(0L);

        assertFalse(persisted.isPresent());
    }

    @Test
    public void updateProduct() {
        Long spWarehouseQuantity = 12L;
        Long moemaWarehouseQuantity = 3L;
        Long sku = 43264L;

        Long totalProductQuantity = spWarehouseQuantity + moemaWarehouseQuantity;

        Product product = ProductMock.createProductMock(spWarehouseQuantity, moemaWarehouseQuantity, sku);

        Mockito.when(repository.exists(product)).thenReturn(false);
        Mockito.when(repository.findBySku(sku)).thenReturn(Optional.of(product));
        Mockito.when(repository.count()).thenReturn(1);

        productService.save(product);

        Optional<Product> persisted = productService.findBySku(sku);

        // asserting initial product state
        assertEquals(1, productService.count());
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

        productService.update(productToUpdate);

        Mockito.when(repository.findBySku(sku)).thenReturn(Optional.of(productToUpdate));

        Optional<Product> persistedAfterUpdate = repository.findBySku(sku);

        // asserting updated product
        assertEquals(1, productService.count());
        assertEquals(sku, persistedAfterUpdate.get().getSku());
        assertEquals("Updated Product Name", persistedAfterUpdate.get().getName());
        assertEquals(updatedTotalProductQuantity, persistedAfterUpdate.get().getInventory().getQuantity());
    }

}
