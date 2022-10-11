package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WarehouseDTO;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.InventoryEntity;
import br.com.blz.testjava.model.ProductEntity;
import br.com.blz.testjava.model.WarehouseEntity;
import br.com.blz.testjava.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepositoryImpl repository;

    @Test
    public void create_new_product() {
        Integer sku = 1564;

        Mockito.when(repository.findProductBySku(sku)).thenReturn(null);

        service.createProduct(buildProductDTO());

        Assertions.assertEquals(sku, buildProduct().getSku());
        Assertions.assertNull(repository.findProductBySku(sku));
    }

    @Test
    public void create_product_already_exist() {

        Mockito.when(repository.findProductBySku(buildProduct().getSku())).thenReturn(buildProduct());

        Assertions.assertThrows(ProductAlreadyExistException.class, () -> service.createProduct(buildProductDTO()));
    }

    @Test
    public void update_product() {
        Integer sku = 1564;

        Mockito.when(repository.findProductBySku(sku)).thenReturn(buildProduct());

        service.updateProduct(buildProductDTO(),sku);

        Assertions.assertEquals(sku, buildProduct().getSku());
        Assertions.assertNotNull(repository.findProductBySku(sku));
    }

    @Test
    public void update_product_not_found() {

        Integer sku = buildProduct().getSku();

        Mockito.when(repository.findProductBySku(sku)).thenReturn(null);

        Assertions.assertThrows(ProductNotFoundException.class, () -> service.updateProduct(buildProductDTO(),sku));
    }

    @Test
    public void recovery_product() {
        Integer sku = 1564;

        Mockito.when(repository.findProductBySku(sku)).thenReturn(buildProduct());

        service.recoveryProduct(sku);

        Assertions.assertEquals(sku, buildProduct().getSku());
        Assertions.assertNotNull(repository.findProductBySku(sku));
    }

    @Test
    public void recovery_product_not_found() {

        Integer sku = buildProduct().getSku();

        Mockito.when(repository.findProductBySku(sku)).thenReturn(null);

        Assertions.assertThrows(ProductNotFoundException.class, () -> service.recoveryProduct(sku));
    }

    @Test
    public void delete_product() {
        Integer sku = 1564;

        Mockito.when(repository.findProductBySku(sku)).thenReturn(buildProduct());

        service.deleteProduct(sku);

        Assertions.assertEquals(sku, buildProduct().getSku());
        Assertions.assertNotNull(repository.findProductBySku(sku));
    }

    @Test
    public void delete_product_not_found() {

        Integer sku = buildProduct().getSku();

        Mockito.when(repository.findProductBySku(sku)).thenReturn(null);

        Assertions.assertThrows(ProductNotFoundException.class, () -> service.deleteProduct(sku));
    }

    private ProductEntity buildProduct() {
        WarehouseEntity wareHouseEccomerce = new WarehouseEntity();
        wareHouseEccomerce.setLocality("SP");
        wareHouseEccomerce.setQuantity(2);
        wareHouseEccomerce.setType("ECOMMERCE");

        WarehouseEntity wareHousePhysicalStore = new WarehouseEntity();
        wareHousePhysicalStore.setLocality("SP");
        wareHousePhysicalStore.setQuantity(1);
        wareHousePhysicalStore.setType("PHYSICAL_STORE");

        List<WarehouseEntity> warehouses = new ArrayList<>();
        warehouses.add(wareHouseEccomerce);
        warehouses.add(wareHousePhysicalStore);

        InventoryEntity inventory = new InventoryEntity();
        inventory.setWarehouses(warehouses);

        ProductEntity product = new ProductEntity();
        product.setSku(1564);
        product.setName("L'oreal");
        product.setInventory(inventory);

        return product;
    }

    private ProductDTO buildProductDTO() {
        WarehouseDTO wareHouseEccomerce = new WarehouseDTO();
        wareHouseEccomerce.setLocality("SP");
        wareHouseEccomerce.setQuantity(2);
        wareHouseEccomerce.setType("ECOMMERCE");

        WarehouseDTO wareHousePhysicalStore = new WarehouseDTO();
        wareHousePhysicalStore.setLocality("SP");
        wareHousePhysicalStore.setQuantity(1);
        wareHousePhysicalStore.setType("PHYSICAL_STORE");

        List<WarehouseDTO> warehouses = new ArrayList<>();
        warehouses.add(wareHouseEccomerce);
        warehouses.add(wareHousePhysicalStore);

        InventoryDTO inventory = new InventoryDTO();
        inventory.setWarehouses(warehouses);

        ProductDTO product = new ProductDTO();
        product.setSku(1564);
        product.setName("L'oreal");
        product.setInventory(inventory);

        return product;
    }
}
