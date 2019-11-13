package br.com.blz.testjava.controller;

import br.com.blz.testjava.component.ProductConversorComponent;
import br.com.blz.testjava.contract.request.InventoryRequest;
import br.com.blz.testjava.contract.request.ProductRequest;
import br.com.blz.testjava.contract.request.WarehouseRequest;
import br.com.blz.testjava.contract.response.InventoryResponse;
import br.com.blz.testjava.contract.response.ProductResponse;
import br.com.blz.testjava.contract.response.WarehouseResponse;
import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    public static final long VALID_SKU = 43264L;
    public static final String VALID_PRODUCT_NAME = "L'Oréal Professionnel";
    public static final long INVALID_SKU = 1111L;
    private WarehouseRequest VALID_WAREHOUSE_REQUEST = null;
    private InventoryRequest VALID_INVENTORY_REQUEST = null;
    private ProductRequest VALID_PRODUCT_REQUEST = null;
    private ProductRequest INVALID_PRODUCT_REQUEST = null;

    private Warehouse VALID_WAREHOUSE = null;
    private Inventory VALID_INVENTORY = null;
    private Product VALID_PRODUCT = null;

    private WarehouseResponse VALID_WAREHOUSE_RESPONSE = null;
    private InventoryResponse VALID_INVENTORY_RESPONSE = null;
    private ProductResponse VALID_PRODUCT_REPONSE = null;

    @Mock
    private ProductConversorComponent productConversorComponent;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setUp(){
        List<WarehouseRequest> warehouses = new ArrayList<>();
        VALID_WAREHOUSE_REQUEST = new WarehouseRequest.Builder()
            .withType("ECOMMERCE")
            .withLocality("SP")
            .withQuantity(12)
            .build();

        warehouses.add(VALID_WAREHOUSE_REQUEST);

        VALID_WAREHOUSE_REQUEST = new WarehouseRequest.Builder()
            .withType("PHYSICAL_STORE")
            .withLocality("MOEMA")
            .withQuantity(3)
            .build();

        warehouses.add(VALID_WAREHOUSE_REQUEST);

        VALID_INVENTORY_REQUEST = new InventoryRequest.Builder()
            .withWarehouses(warehouses)
            .build();

        VALID_PRODUCT_REQUEST = new ProductRequest.Builder()
            .withName(VALID_PRODUCT_NAME)
            .withSku(VALID_SKU)
            .withInventory(VALID_INVENTORY_REQUEST)
            .build();

        INVALID_PRODUCT_REQUEST = new ProductRequest.Builder()
            .withSku(43264L)
            .withInventory(VALID_INVENTORY_REQUEST)
            .build();

        List<Warehouse> warehousesModel = new ArrayList<>();
        VALID_WAREHOUSE = new Warehouse.Builder()
            .withType("ECOMMERCE")
            .withLocality("SP")
            .withQuantity(12)
            .build();

        warehousesModel.add(VALID_WAREHOUSE);

        VALID_WAREHOUSE = new Warehouse.Builder()
            .withType("PHYSICAL_STORE")
            .withLocality("MOEMA")
            .withQuantity(3)
            .build();

        warehousesModel.add(VALID_WAREHOUSE);

        VALID_INVENTORY = new Inventory.Builder()
            .withWarehouses(warehousesModel)
            .build();

        VALID_PRODUCT = new Product.Builder()
            .withName(VALID_PRODUCT_NAME)
            .withSku(VALID_SKU)
            .withInventory(VALID_INVENTORY)
            .build();

        List<WarehouseResponse> warehousesResponse = new ArrayList<>();
        VALID_WAREHOUSE_RESPONSE = new WarehouseResponse.Builder()
            .withType("ECOMMERCE")
            .withLocality("SP")
            .withQuantity(12)
            .build();

        warehousesResponse.add(VALID_WAREHOUSE_RESPONSE);

        VALID_WAREHOUSE_RESPONSE = new WarehouseResponse.Builder()
            .withType("PHYSICAL_STORE")
            .withLocality("MOEMA")
            .withQuantity(3)
            .build();

        warehousesResponse.add(VALID_WAREHOUSE_RESPONSE);

        VALID_INVENTORY_RESPONSE = new InventoryResponse.Builder()
            .withWarehouses(warehousesResponse)
            .withQuantity(15)
            .build();

        VALID_PRODUCT_REPONSE = new ProductResponse.Builder()
            .withName(VALID_PRODUCT_NAME)
            .withSku(VALID_SKU)
            .withInventory(VALID_INVENTORY_RESPONSE)
            .withIsMarketable(true)
            .build();

    }


    @Test
    public void controllerShouldCreateAProductWithSuccess() throws Exception {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(productConversorComponent.requestToEntityConverter(VALID_PRODUCT_REQUEST)).thenReturn(VALID_PRODUCT);
        when(productService.create(VALID_PRODUCT)).thenReturn(VALID_PRODUCT);
        when(productConversorComponent.entityToResponseConverter(VALID_PRODUCT)).thenReturn(VALID_PRODUCT_REPONSE);
        Assert.assertNotNull(productController.create(VALID_PRODUCT_REQUEST, result));
    }

    @Test
    public void controllerShouldReturnErrorsWhenNameIsNotPresentInRequest() throws Exception {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, productController.create(INVALID_PRODUCT_REQUEST, result).getStatusCode());
    }

    @Test
    public void controllerShouldReturnProductWhenReceivesValidSku(){
        when(productService.findBySku(VALID_SKU)).thenReturn(VALID_PRODUCT);
        when(productConversorComponent.entityToResponseConverter(VALID_PRODUCT)).thenReturn(VALID_PRODUCT_REPONSE);
        Assert.assertEquals(HttpStatus.OK, productController.findProductBySku(VALID_SKU).getStatusCode());
    }

    @Test(expected = NotFoundException.class)
    public void controllerShouldThrowExcpetionWhenReceivesInvalidSku(){
        when(productService.findBySku(INVALID_SKU)).thenThrow(NotFoundException.class);
        Assert.assertEquals(HttpStatus.NOT_FOUND, productController.findProductBySku(INVALID_SKU).getStatusCode());
    }


    @Test
    public void controllerShouldDeleteProductWhenReceivesValidSku(){
        Assert.assertEquals(HttpStatus.NO_CONTENT, productController.remove(VALID_SKU).getStatusCode());
    }
}
