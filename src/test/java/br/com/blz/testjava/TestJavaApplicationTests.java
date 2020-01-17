package br.com.blz.testjava;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.service.api.ProductController;
import br.com.blz.testjava.service.data.ProductDataHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.internal.Bytes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {

    Product product = null;
    ObjectMapper objectMapper;

    @Mock
    ObjectMapper objectMapperMock;

    @Mock
    ProductDataHandler productDataHandler;

    @InjectMocks
    ProductController productController;

    @Before
    public void config() {
        objectMapper = new ObjectMapper();
        product = new Product();
        product.setSku(1L);
        Inventory inventory = new Inventory();
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("Sao Paulo");
        warehouse.setQuantity(10L);
        warehouse.setType("TEST");
        inventory.setWarehouses(new ArrayList<>());
        inventory.getWarehouses().add(warehouse);
        product.setInventory(inventory);
    }

    @Test
    public void createProductSucess() {

        String request = null;
        try {
            request = objectMapper.writeValueAsString(product);
            doReturn(objectMapper.readValue(request.getBytes(), Product.class))
                .when(objectMapperMock.readValue(any(byte[].class),any(Class.class)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productController.createProduct(request);
    }

    @Test
    public void updateProductSucess() {
    }

    @Test
    public void removeProductSucess() {
    }

    @Test
    public void getProductSucess() {
    }
}
