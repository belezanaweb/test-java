package br.com.blz.testjava.product;

import br.com.blz.testjava.exception.NotFoundProductException;
import br.com.blz.testjava.exception.ProductSkuExistsException;
import br.com.blz.testjava.inventory.InventoryEntity;
import br.com.blz.testjava.inventory.InventoryRepository;
import br.com.blz.testjava.warehouse.WarehouseEntity;
import br.com.blz.testjava.warehouse.WarehouseRepository;
import br.com.blz.testjava.warehouse.WarehouseType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

import java.util.LinkedList;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public static final Long BELEZA_NA_WEB_PRODUCT_SKU = 12345L;
    private static final String BELEZA_NA_WEB_PRODUCT_NAME = "BELEZA NA WEB" ;
    private static final String WAREHOUSE_LOCALITY_MG = "MG";
    private static final String WAREHOUSE_LOCALITY_SP = "SP";

    @Test
    public void findBySkuWithSuccess() throws NotFoundProductException {

        Mockito.when(productRepository.findBySku(BELEZA_NA_WEB_PRODUCT_SKU))
            .thenReturn(productMocked());

        ProductEntity productEntity = productService.findBySku(BELEZA_NA_WEB_PRODUCT_SKU);

        assertNotNull(productEntity);
        assertTrue(productEntity.isMarketable());
        assertTrue(productEntity.getInventory().getQuantity() == 5);
    }

    @Test(expected = NotFoundProductException.class)
    public void findBySkuWithNotFoundProductError() throws NotFoundProductException {
        Mockito.when(productRepository.findBySku(BELEZA_NA_WEB_PRODUCT_SKU))
            .thenReturn(null);
        productService.findBySku(BELEZA_NA_WEB_PRODUCT_SKU);
    }

    @Test
    public void saveProductWithSucess() throws ProductSkuExistsException {
        Mockito.when(productRepository.save(productMocked())).thenReturn(productMocked());

        ProductEntity productEntity = productService.save(productBuilded());

        assertNotNull(productEntity);
    }

    private ProductEntity productMocked(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setSku(BELEZA_NA_WEB_PRODUCT_SKU);
        productEntity.setName(BELEZA_NA_WEB_PRODUCT_NAME);
        return productEntity;
    }

    private LinkedList<WarehouseEntity> warehouseMocked(){
        WarehouseEntity warehouseEntityMock1 = new WarehouseEntity();
        warehouseEntityMock1.setId(1L);
        warehouseEntityMock1.setLocality(WAREHOUSE_LOCALITY_MG);
        warehouseEntityMock1.setType(WarehouseType.PHYSICAL_STORE);
        warehouseEntityMock1.setQuantity(2);

        WarehouseEntity warehouseEntityMock2 = new WarehouseEntity();
        warehouseEntityMock2.setId(2L);
        warehouseEntityMock2.setLocality(WAREHOUSE_LOCALITY_SP);
        warehouseEntityMock2.setType(WarehouseType.ECOMMERCE);
        warehouseEntityMock2.setQuantity(3);

        LinkedList<WarehouseEntity> warehouseEntityList = new LinkedList<>();
        warehouseEntityList.add(warehouseEntityMock1);
        warehouseEntityList.add(warehouseEntityMock2);
        return warehouseEntityList;
    }

    private InventoryEntity inventoryMocked(){
        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setId(1L);
        return inventoryEntity;
    }

    private ProductEntity productBuilded(){
        ProductEntity productEntity = productMocked();
        InventoryEntity inventoryEntity = inventoryMocked();
        inventoryEntity.setWarehouses(warehouseMocked());
        productEntity.setInventory(inventoryEntity);
        return productEntity;
    }

}
