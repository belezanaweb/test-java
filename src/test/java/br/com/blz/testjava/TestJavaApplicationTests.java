package br.com.blz.testjava;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.service.api.ProductController;
import br.com.blz.testjava.service.data.ProductDataHandler;
import br.com.blz.testjava.service.data.ProductDataHandlerImpl;
import br.com.blz.testjava.service.data.exception.ProductSkuAlreadyExistsException;
import br.com.blz.testjava.service.data.exception.SkuNotProvidedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {

    Product product = null;
    ObjectMapper objectMapper;

    @Mock
    ObjectMapper objectMapperMock;

    @Mock
    ProductDataHandler productDataHandlerMock;

    @InjectMocks
    ProductController productController;

    @Spy
    HashMap<Long, Product> productMapSpy;

    @Before
    public void config() {
        objectMapper = new ObjectMapper();
        createProduct();
        initMocks(objectMapperMock);
        initMocks(productDataHandlerMock);
    }

    private void createProduct() {
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
        productMapSpy.put(product.getSku(), product);
    }

    @Test
    public void createProductSucess() {
        //arrange
        String request = null;
        try {
            request = objectMapper.writeValueAsString(product);
            Product product = objectMapper.readValue(request.getBytes(), Product.class);
            when(objectMapperMock.readValue(any(byte[].class), any(Class.class)))
                .thenReturn(product);
            when(productDataHandlerMock.addProduct(any(Product.class))).thenReturn(product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProductSkuAlreadyExistsException e) {
            e.printStackTrace();
        } catch (SkuNotProvidedException e) {
            e.printStackTrace();
        }
        //act
        ResponseEntity responseEntity = productController.createProduct(request);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void createProductSkuNotProvided() {
        //arrange
        String request = null;
        try {
            request = objectMapper.writeValueAsString(product);
            Product product = objectMapper.readValue(request.getBytes(), Product.class);
            when(objectMapperMock.readValue(any(byte[].class), any(Class.class)))
                .thenReturn(product);
            when(productDataHandlerMock.addProduct(any(Product.class))).thenThrow(SkuNotProvidedException.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProductSkuAlreadyExistsException e) {
            e.printStackTrace();
        } catch (SkuNotProvidedException e) {
            e.printStackTrace();
        }
        //act
        ResponseEntity responseEntity = productController.createProduct(request);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void createProductAlreadyExists() {
        //arrange
        String request = null;
        try {
            request = objectMapper.writeValueAsString(product);
            Product product = objectMapper.readValue(request.getBytes(), Product.class);
            when(objectMapperMock.readValue(any(byte[].class), any(Class.class)))
                .thenReturn(product);
            when(productDataHandlerMock.addProduct(any(Product.class))).thenThrow(ProductSkuAlreadyExistsException.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProductSkuAlreadyExistsException e) {
            e.printStackTrace();
        } catch (SkuNotProvidedException e) {
            e.printStackTrace();
        }
        //act
        ResponseEntity responseEntity = productController.createProduct(request);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void createProductParseFail() {
        //arrange
        String request = null;
        try {
            request = objectMapper.writeValueAsString(product);
            Product product = objectMapper.readValue(request.getBytes(), Product.class);
            when(objectMapperMock.readValue(any(byte[].class), any(Class.class)))
                .thenThrow(IOException.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //act
        ResponseEntity responseEntity = productController.createProduct(request);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void updateProductSucess() {

        //arrange
        String request = null;
        try {
            request = objectMapper.writeValueAsString(product);
            Product product = objectMapper.readValue(request.getBytes(), Product.class);
            when(objectMapperMock.readValue(any(byte[].class), any(Class.class)))
                .thenReturn(product);
            when(productDataHandlerMock.updateProduct(any(Product.class))).thenReturn(product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //act
        ResponseEntity responseEntity = productController.updateProduct(request);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void updateProductParseFail() {

        //arrange
        String request = null;
        try {
            request = objectMapper.writeValueAsString(product);
            Product product = objectMapper.readValue(request.getBytes(), Product.class);
            when(objectMapperMock.readValue(any(byte[].class), any(Class.class)))
                .thenReturn(product);
            when(productDataHandlerMock.updateProduct(any(Product.class))).thenThrow(IOException.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //act
        ResponseEntity responseEntity = productController.updateProduct(request);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void removeProductSucess() {

        when(productDataHandlerMock.removeProduct(anyLong())).thenReturn(Boolean.TRUE);
        ResponseEntity responseEntity = productController.removeProduct(1L);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void removeProductNotFound() {
        //arrange
        when(productDataHandlerMock.removeProduct(anyLong())).thenReturn(Boolean.FALSE);
        //act
        ResponseEntity responseEntity = productController.removeProduct(1L);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }

    @Test
    public void getProductSucess() {

        //arrange
        when(productDataHandlerMock.getProduct(anyLong())).thenReturn(product);
        try {
            String request = objectMapper.writeValueAsString(product);
            when(objectMapperMock.writeValueAsString(anyObject())).thenReturn(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //act
        ResponseEntity responseEntity = productController.getProduct(1L);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void getProductParseFail() {

        //arrange
        when(productDataHandlerMock.getProduct(anyLong())).thenReturn(product);
        try {
            String request = objectMapper.writeValueAsString(product);
            when(objectMapperMock.writeValueAsString(anyObject())).thenThrow(JsonProcessingException.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //act
        ResponseEntity responseEntity = productController.getProduct(1L);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void getProductNotFound() {

        //arrange
        when(productDataHandlerMock.getProduct(anyLong())).thenReturn(null);
        //act
        ResponseEntity responseEntity = productController.getProduct(1L);
        //assertion
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }
}
