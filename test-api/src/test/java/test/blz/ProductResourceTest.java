package test.blz;

import static org.mockito.Mockito.doThrow;
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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import test.blz.bean.Inventory;
import test.blz.bean.ProductCreateRequest;
import test.blz.bean.ProductUpdateRequest;
import test.blz.bean.ProductVO;
import test.blz.bean.Warehouse;
import test.blz.bean.WarehouseType;
import test.blz.exception.ProductAlreadyExistsException;
import test.blz.resource.ProductResource;
import test.blz.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductResourceTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductResource resource;

    @Test
    public void testCreateProduct () throws ProductAlreadyExistsException {

        final ProductCreateRequest request = buildCreateRequest(1L);

        final ResponseEntity<?> responseEntity = resource.createProduct(request);

        verify(service, times(1)).createProduct(Mockito.any(ProductVO.class));

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        final Object body = responseEntity.getBody();
        Assert.assertNull(body);
    }

    @Test
    public void testUpdateProduct () {

        final ProductUpdateRequest request = buildUpdateRequest();

        final ResponseEntity<?> responseEntity = resource.editProduct(request);

        verify(service, times(1)).updateProduct(Mockito.any(ProductVO.class));

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final Object body = responseEntity.getBody();
        Assert.assertNotNull(body);
    }


    @Test
    public void testCreateProductThatAlreadyExists() throws ProductAlreadyExistsException {

        final ProductCreateRequest request = buildCreateRequest(1L);

        doThrow(new ProductAlreadyExistsException()).when(service).createProduct(request.convertToVO());

        try {
            resource.createProduct(request);
        } catch (ResponseStatusException e) {
            Assert.assertEquals(HttpStatus.FORBIDDEN, e.getStatus());
        }

    }

    @Test
    public void testClearAllCache () {

        final ResponseEntity<?> responseEntity = resource.clearCache();

        verify(service, times(1)).clearAllCache();

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        final Object body = responseEntity.getBody();
        Assert.assertNull(body);
    }

    @Test
    public void testClearProductCache () {

        final Long sku = 1L;

        final ResponseEntity<?> responseEntity = resource.deleteProduct(sku);

        verify(service, times(1)).deleteProcuct(sku);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        final Object body = responseEntity.getBody();
        Assert.assertNull(body);
    }

    @Test
    public void testFindProduct () {

        final Long sku = 1L;

        when(service.findProduct(sku)).thenReturn(ProductVO.builder().build());

        final ResponseEntity<?> responseEntity = resource.findProduct(sku);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final Object body = responseEntity.getBody();
        Assert.assertNotNull(body);
    }

    @Test
    public void testFindProductNotCreated () {

        final Long sku = 1L;

        when(service.findProduct(sku)).thenReturn(null);

        final ResponseEntity<?> responseEntity = resource.findProduct(sku);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        final Object body = responseEntity.getBody();
        Assert.assertNull(body);
    }

    ProductCreateRequest buildCreateRequest(Long sku){

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
                .warehouses(warehouses)
                .build();

        return ProductCreateRequest.builder()
                .sku(sku)
                .name("produto teste")
                .inventory(inventory)
                .build();
    }

    ProductUpdateRequest buildUpdateRequest(){

        List<Warehouse>warehouses = new ArrayList<>();
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
                .warehouses(warehouses)
                .build();

        return ProductUpdateRequest.builder()
                .sku(1L)
                .name("produto teste")
                .inventory(inventory)
                .build();
    }
}
