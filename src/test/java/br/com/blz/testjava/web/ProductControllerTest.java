package br.com.blz.testjava.web;


import br.com.blz.testjava.data.dto.InventoryDTO;
import br.com.blz.testjava.data.dto.PointOfServiceTypeDTO;
import br.com.blz.testjava.data.dto.ProductDTO;
import br.com.blz.testjava.data.dto.WarehouseDTO;
import br.com.blz.testjava.data.response.ProductResponse;
import br.com.blz.testjava.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Spy
    private ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void GIVEN_PRODUCT_PERSISTED() {
        ResponseEntity response = productController.create(productDTO("123"));
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void GIVEN_PRODUCT_UPDATE_PERSISTED() {

        ResponseEntity createResponse = productController.create(productDTO("1234"));
        ResponseEntity updateResponse = productController.update(productToUpdate(), "1234");

        Assert.assertNotNull(createResponse);
        Assert.assertNotNull(updateResponse);
        Assert.assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        final String skuName = ((ProductResponse) updateResponse.getBody()).getName();
        Assert.assertEquals(skuName, productToUpdate().getName());
    }


    @Test
    public void GIVEN_PRODUCT_FOUNDED() {

        ResponseEntity createResponse = productController.create(productDTO("12345"));
        ResponseEntity getResponse = productController.get("12345");
        Assert.assertNotNull(createResponse);
        Assert.assertNotNull(getResponse);
        final String skuCode = ((ProductResponse) getResponse.getBody()).getSku();

        Assert.assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        Assert.assertEquals(skuCode, "12345");

    }

    @Test
    public void GIVEN_PRODUCT_DELETED() {

        ResponseEntity createResponse = productController.create(productDTO("123456"));
        ResponseEntity deleteResponse = productController.delete("123456");
        ResponseEntity getDeletedProduct = productController.get("123456");

        Assert.assertNotNull(createResponse);
        Assert.assertNotNull(deleteResponse);
        Assert.assertNotNull(getDeletedProduct);


        Assert.assertEquals(HttpStatus.NOT_FOUND, getDeletedProduct.getStatusCode());

    }

    @Test
    public void GIVEN_PRODUCT_NOT_FOUND() {

        ResponseEntity productNotFound = productController.get("777777");
        Assert.assertNotNull(productNotFound);
        Assert.assertEquals(HttpStatus.NOT_FOUND, productNotFound.getStatusCode());
    }

    @Test
    public void GIVEN_PRODUCT_IS_MARKETABLE() {

        ResponseEntity createResponse = productController.create(productDTO("1234567"));
        ResponseEntity getResponse = productController.get("1234567");

        Assert.assertNotNull(createResponse);
        Assert.assertNotNull(getResponse);


        Assert.assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        Assert.assertTrue(((ProductResponse) getResponse.getBody()).isMarketable());
    }

    @Test
    public void GIVEN_PRODUCT_IS_DUPLICATED() {

        ResponseEntity firstProduct = productController.create(productDTO("12345678"));
        ResponseEntity secondProduct = productController.create(productDTO("12345678"));

        Assert.assertNotNull(firstProduct);
        Assert.assertNotNull(secondProduct);


        Assert.assertEquals(HttpStatus.OK, firstProduct.getStatusCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, secondProduct.getStatusCode());

    }


    private ProductDTO productDTO(final String sku) {

        WarehouseDTO warehouseDTO = WarehouseDTO.builder()
            .locality("teste")
            .quantity(1)
            .type(PointOfServiceTypeDTO.ECOMMERCE)
            .build();

        InventoryDTO inventoryDTO = InventoryDTO
            .builder()
            .warehouses(List.of(warehouseDTO))
            .build();

        return ProductDTO.builder()
            .sku(sku)
            .name("teste sku")
            .inventory(inventoryDTO)
            .build();
    }

    private ProductDTO productToUpdate() {

        WarehouseDTO warehouseDTO = WarehouseDTO.builder()
            .locality("teste-mudanca")
            .quantity(1)
            .type(PointOfServiceTypeDTO.PHYSICAL_STORE)
            .build();

        InventoryDTO inventoryDTO = InventoryDTO
            .builder()
            .warehouses(List.of(warehouseDTO))
            .build();

        return ProductDTO.builder()
            .sku("123")
            .name("mudado")
            .inventory(inventoryDTO)
            .build();
    }
}
