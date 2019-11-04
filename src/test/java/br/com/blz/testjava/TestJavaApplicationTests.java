package br.com.blz.testjava;

import br.com.blz.testjava.http.ProductController;
import br.com.blz.testjava.http.data.request.InventoryRequest;
import br.com.blz.testjava.http.data.request.ProductRequest;
import br.com.blz.testjava.http.data.request.WarehouseRequest;
import br.com.blz.testjava.http.data.request.WarehouseTypeRequest;
import br.com.blz.testjava.http.data.response.ProductResponse;
import br.com.blz.testjava.usecase.data.Inventory;
import br.com.blz.testjava.usecase.data.WarehouseType;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {
    @Autowired
    private ProductController controller;



    @Test
    public void testCreateProduct(){

        ProductRequest request = getProductRequest();
        ProductResponse response = controller.createProduct(request);

        assertNotNull(response);
        assertEquals(request.getSku(), response.getSku());
        assertEquals(request.getName(), response.getName());
        assertEquals(Integer.valueOf(15), response.getInventory().getQuantity());
        assertEquals(Boolean.TRUE, response.getMarketable());

    }

    @Test
    public void testGetProduct(){
        ProductRequest request = getProductRequest();
        ProductResponse response = controller.getProductBySku(request.getSku());

        assertNotNull(response);
        assertEquals(request.getSku(), response.getSku());
        assertEquals(request.getName(), response.getName());
        assertEquals(Integer.valueOf(15), response.getInventory().getQuantity());
        assertEquals(Boolean.TRUE, response.getMarketable());
    }

    @Test
    public void testUpdateProduct(){
        ProductRequest request = getProductRequest();

        request.setName("Test 2");
        request.getInventory().getWarehouses().forEach(warehouseRequest -> {
            warehouseRequest.setQuantity(0);
        });

        ProductResponse response = controller.updateProduct(request.getSku(), request);

        assertNotNull(response);
        assertEquals(request.getSku(), response.getSku());
        assertEquals(request.getName(), response.getName());
        assertEquals(Integer.valueOf(0), response.getInventory().getQuantity());
        assertEquals(Boolean.FALSE, response.getMarketable());

    }




    @Test
    public void testDeleteProduct() {
        ProductRequest request = getProductRequest();
        controller.deleteProduct(request.getSku());
        ProductResponse response = controller.getProductBySku(request.getSku());

        assertNull(response);


    }



    private ProductRequest getProductRequest(){
        return ProductRequest.builder()
            .sku(12345L)
            .name("Test Product")
            .inventory(InventoryRequest.builder()
                .warehouses(Arrays.asList(
                    WarehouseRequest.builder()
                        .locality("SP")
                        .quantity(12)
                        .type(WarehouseTypeRequest.ECOMMERCE)
                        .build(),
                    WarehouseRequest.builder()
                        .locality("SP")
                        .quantity(3)
                        .type(WarehouseTypeRequest.PHYSICAL_STORE)
                        .build()
                ))
                .build())
            .build();
    }


}
