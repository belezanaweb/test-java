package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.BadRequestException;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    public static final long VALID_SKU = 43264L;
    public static final long INVALID_SKU = 111111L;
    public static final String VALID_PRODUCT_NAME = "L'Oréal Professionnel";
    public static final String VALID_UPDATED_PRODUCT_NAME = "L'Oréal Professional";
    private Warehouse VALID_WAREHOUSE = null;
    private Inventory VALID_INVENTORY = null;
    private Product VALID_PRODUCT = null;
    private Product VALID_UPDATED_PRODUCT = null;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void setUp(){

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

        VALID_UPDATED_PRODUCT = new Product.Builder()
            .withName(VALID_UPDATED_PRODUCT_NAME)
            .withSku(VALID_SKU)
            .withInventory(VALID_INVENTORY)
            .build();

    }

    @Test
    public void serviceShouldCreateAProductWithSuccess() {
        when(productRepository.findBySku(VALID_SKU)).thenReturn(Optional.empty());
        when(productRepository.save(VALID_PRODUCT)).thenReturn(VALID_PRODUCT);
        Assert.assertNotNull(productService.create(VALID_PRODUCT));
    }

    @Test
    public void serviceShouldUpdateAProductWithSuccess() {
        when(productRepository.findBySku(VALID_SKU)).thenReturn(Optional.of(VALID_PRODUCT));
        when(productRepository.save(VALID_PRODUCT)).thenReturn(VALID_UPDATED_PRODUCT);
        Assert.assertNotNull(productService.update(VALID_SKU, VALID_PRODUCT));
    }

    @Test(expected = BadRequestException.class)
    public void controllerShouldThrowExcpetionOnUpdateWhenReceivesInvalidSku(){
        Assert.assertNotNull(productService.update(INVALID_SKU, VALID_PRODUCT));
    }

    @Test(expected = NotFoundException.class)
    public void controllerShouldThrowExcpetionOnUpdateWhenProductDoesntExists(){
        when(productRepository.findBySku(VALID_SKU)).thenReturn(Optional.empty());
        when(productRepository.save(VALID_PRODUCT)).thenReturn(VALID_UPDATED_PRODUCT);
        Assert.assertNotNull(productService.update(VALID_SKU, VALID_PRODUCT));
    }

    @Test
    public void serviceShouldReturnValidProductWhenReceiveValidSku() {
        when(productRepository.findBySku(VALID_SKU)).thenReturn(Optional.of(VALID_PRODUCT));
        Assert.assertNotNull(productService.findBySku(VALID_SKU));
        Assert.assertEquals(VALID_PRODUCT.getSku(), productService.findBySku(VALID_SKU).getSku());
        Assert.assertEquals(VALID_PRODUCT.getName(), productService.findBySku(VALID_SKU).getName());
    }

}
