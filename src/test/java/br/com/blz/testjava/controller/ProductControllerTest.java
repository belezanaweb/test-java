package br.com.blz.testjava.controller;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findProductSuccess() {
        Product productMock = Product.builder()
                                        .sku(18)
                                        .name(Strings.EMPTY)
                                        .build();
        when(productService.findProduct(18)).thenReturn(productMock);
        productController.findProduct(productMock.getSku());
        assertEquals(productMock, productController.findProduct(18));
    }

    @Test
    public void findProductNotFound() {
        doThrow(ProductNotFoundException.class).when(productService).findProduct(anyInt());
        ProductNotFoundException exception = null;
        try {
            productController.findProduct(anyInt());
        } catch (ProductNotFoundException t) {
            exception = t;
        }
        assertNotNull(exception);
    }

    @Test
    public void createProductSuccess(){
        Product productMock = Product.builder()
                                        .sku(1)
                                        .name(Strings.EMPTY)
                                        .build();
        when(productService.createProduct(productMock)).thenReturn(productMock);
        productController.createProduct(productMock);
        assertThat(productMock.getSku(), equalTo(1));
    }

    @Test
    public void createProductAlreadyExist(){
        doThrow(ProductAlreadyExistException.class).when(productService).createProduct(new Product());
        ProductAlreadyExistException exception = null;
        try {
            productController.createProduct(new Product());
        } catch (ProductAlreadyExistException t) {
            exception = t;
        }
        assertNotNull(exception);
    }

    @Test
    public void modifyProductSuccess(){
        Product productMock = Product.builder()
            .sku(18)
            .name(Strings.EMPTY)
            .build();
        when(productService.modifyProduct(productMock.getSku(), productMock)).thenReturn(productMock);
        productController.modifyProduct(productMock.getSku(), productMock);
        assertThat(productMock.getSku(), equalTo(18));
    }

    @Test
    public void modifyProductNotFound(){
        Product productMock = Product.builder()
            .sku(18)
            .name(Strings.EMPTY)
            .build();
        doThrow(ProductNotFoundException.class).when(productService).modifyProduct(1, productMock);
        ProductNotFoundException exception = null;
        try {
            productController.modifyProduct(1, productMock);
        } catch (ProductNotFoundException t) {
            exception = t;
        }
        assertNotNull(exception);
    }

    @Test
    public void deleteProductSuccess(){
        Product productMock = Product.builder()
            .sku(18)
            .name(Strings.EMPTY)
            .build();
        productController.deleteProduct(productMock.getSku());
        verify(productService).deleteProduct(productMock.getSku());
    }

    @Test
    public void deleteProductNotFound(){
        doThrow(ProductNotFoundException.class).when(productService).deleteProduct(1);
        ProductNotFoundException exception = null;
        try {
            productController.deleteProduct(1);
        } catch (ProductNotFoundException t) {
            exception = t;
        }
        assertNotNull(exception);
    }
}
