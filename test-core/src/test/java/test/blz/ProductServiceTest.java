package test.blz;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import test.blz.bean.Inventory;
import test.blz.bean.ProductVO;
import test.blz.bean.Warehouse;
import test.blz.bean.WarehouseType;
import test.blz.exception.ProductAlreadyExistsException;
import test.blz.repository.ProductCache;
import test.blz.service.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductCache productCache;

    @InjectMocks
    private ProductServiceImpl service;

    private final Long sku = 1L;

    @Test(expected = ProductAlreadyExistsException.class)
    public void testCreateProductThatAlreadyExists () throws ProductAlreadyExistsException {

        final ProductVO productVO = buildProduct();

        when(productCache.retrieveFromCache(sku)).thenReturn(productVO);
        service.createProduct(productVO);
    }

    @Test
    public void testCreateProduct () throws ProductAlreadyExistsException {
        final ProductVO productVO = buildProduct();
        when(productCache.retrieveFromCache(sku)).thenReturn(null);
        service.createProduct(productVO);
    }

    @Test
    public void testUpdateProduct () {
        final ProductVO productVO = buildProduct();
        service.updateProduct(productVO);
        verify(productCache, times(1)).putOnCache(productVO);
    }

    @Test
    public void testFindProductThatExists(){
        final ProductVO productVO = buildProduct();
        when(productCache.retrieveFromCache(sku)).thenReturn(productVO);
        final ProductVO product = service.findProduct(sku);
        Assert.assertNotNull(product);
    }

    @Test
    public void testFindProductThatDoesNotExists(){
        when(productCache.retrieveFromCache(sku)).thenReturn(null);
        final ProductVO product = service.findProduct(sku);
        Assert.assertNull(product);
    }

    @Test
    public void testClearAllCache () {
        service.clearAllCache();
        verify(productCache, times(1)).clearAll();
    }

    @Test
    public void testClearCacheForProduct () {
        service.deleteProcuct(sku);
        verify(productCache, times(1)).removeFromCache(sku);
    }


    private ProductVO buildProduct(){

        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(Warehouse.builder()
                .locality("sao paulo")
                .quantity(1)
                .type(WarehouseType.ECOMMERCE)
                .build());

        warehouses.add(Warehouse.builder()
                .locality("sao paulo")
                .quantity(1)
                .type(WarehouseType.PHYSICAL_STORE)
                .build());

        final Inventory inventory = Inventory.builder()
                .quantity(1)
                .warehouses(warehouses)
                .build();

        return ProductVO.builder()
                .sku(1L)
                .name("produto teste")
                .inventory(inventory)
                .build();
    }
}
