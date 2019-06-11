package test.blz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import test.blz.bean.Inventory;
import test.blz.bean.ProductVO;
import test.blz.bean.Warehouse;
import test.blz.bean.WarehouseType;
import test.blz.repository.ProductCacheImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductCacheTest {

    @Mock
    private RedisUtils<ProductVO> redisUtils;

    @InjectMocks
    private ProductCacheImpl cache;

    private static final String key = "PRODUCT";
    private final static Long sku = 1L;

    @Test
    public void testPutOnCache(){
        final ProductVO productVO = buildProduct();
        cache.putOnCache(productVO);
        Mockito.verify(redisUtils, Mockito.times(1)).putMap(key, productVO.getSku(), productVO);
        Mockito.verify(redisUtils, Mockito.times(1)).setExpire(key, 1, TimeUnit.DAYS);
    }

    @Test
    public void testRetrieveFromCache(){
        cache.retrieveFromCache(sku);
        Mockito.verify(redisUtils, Mockito.times(1)).getMapAsSingleEntry(key, sku);
    }

    @Test
    public void testRemoveFromCache(){
        cache.removeFromCache(sku);
        Mockito.verify(redisUtils, Mockito.times(1)).removeFromCache(key, sku);
    }

    @Test
    public void clearCache(){
         cache.clearAll();
         Mockito.verify(redisUtils, Mockito.times(1)).clearAll(Mockito.anyString());
    }

    ProductVO buildProduct(){

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
