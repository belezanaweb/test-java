package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WareHouseDTO;
import br.com.blz.testjava.exceptions.ExistentProductException;
import br.com.blz.testjava.exceptions.InexistentProductException;
import br.com.blz.testjava.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductServiceTests {

    @Mock
    ProductDTO productDTO;

    @Mock
    WareHouseDTO wareHouseDTO;

    @Mock
    InventoryDTO inventoryDTO;

    @Autowired
    ProductService productService;

    @Test
    public void shouldCreateProduct() {

        List<WareHouseDTO> warehouses = new ArrayList<>();
        warehouses.add(wareHouseDTO);
        warehouses.add(wareHouseDTO);
        warehouses.add(wareHouseDTO);

        Mockito.when(productDTO.getSku()).thenReturn(5645);
        Mockito.when(productDTO.getInventory()).thenReturn(inventoryDTO);
        Mockito.when(inventoryDTO.getWarehouses()).thenReturn(warehouses);

        Product product = productService.createProduct(productDTO);

        Assertions.assertEquals(product.getSku(), 5645);
    }

    @Test
    public void shouldCalculateInventory() {
        List<WareHouseDTO> warehouses = new ArrayList<>();
        warehouses.add(wareHouseDTO);
        warehouses.add(wareHouseDTO);
        warehouses.add(wareHouseDTO);

        Mockito.when(productDTO.getSku()).thenReturn(12);
        Mockito.when(productDTO.getInventory()).thenReturn(inventoryDTO);
        Mockito.when(inventoryDTO.getWarehouses()).thenReturn(warehouses);
        Mockito.when(wareHouseDTO.getQuantity()). thenReturn(5);

        Product product = productService.createProduct(productDTO);

        Assertions.assertEquals(product.getInventory().getQuantity(), 15);
    }

    @Test
    public void shouldThrowExistentProductException(){

        Assertions.assertThrows(ExistentProductException.class, () -> {
            List<WareHouseDTO> warehouses = new ArrayList<>();
            warehouses.add(wareHouseDTO);
            Mockito.when(productDTO.getSku()).thenReturn(56);
            Mockito.when(productDTO.getInventory()).thenReturn(inventoryDTO);
            Mockito.when(inventoryDTO.getWarehouses()).thenReturn(warehouses);

            productService.createProduct(productDTO);

            productService.createProduct(productDTO);
        });
    }

    @Test
    public void shouldThrowInexistentProductException() {
        Assertions.assertThrows(InexistentProductException.class, () -> {
            productService.findProductBySKU(3261);
        });
    }

    @Test
    public void shouldDeleteProduct() {
        List<WareHouseDTO> warehouses = new ArrayList<>();
        warehouses.add(wareHouseDTO);
        Mockito.when(productDTO.getSku()).thenReturn(5);
        Mockito.when(productDTO.getInventory()).thenReturn(inventoryDTO);
        Mockito.when(inventoryDTO.getWarehouses()).thenReturn(warehouses);

        productService.createProduct(productDTO);

        productService.deleteProductBySku(5);
    }

    @Test
    public void shouldUpdateProduct() {
        List<WareHouseDTO> warehouses = new ArrayList<>();
        warehouses.add(wareHouseDTO);
        warehouses.add(wareHouseDTO);
        warehouses.add(wareHouseDTO);

        List<WareHouseDTO> updatedWarehouses = new ArrayList<>();
        updatedWarehouses.add(wareHouseDTO);
        updatedWarehouses.add(wareHouseDTO);

        Mockito.when(productDTO.getSku()).thenReturn(9874);
        Mockito.when(productDTO.getName()).thenReturn("CREME").thenReturn("PERFUME");
        Mockito.when(productDTO.getInventory()).thenReturn(inventoryDTO);
        Mockito.when(inventoryDTO.getWarehouses()).thenReturn(warehouses).thenReturn(updatedWarehouses);
        Mockito.when(wareHouseDTO.getQuantity()). thenReturn(5);

        Product product = productService.createProduct(productDTO);

        Assertions.assertEquals(product.getInventory().getQuantity(), 15);
        Assertions.assertEquals(product.getName(), "CREME");
        Assertions.assertEquals(product.getSku(), 9874);

        Product updatedProduct = productService.editProduct(9874, productDTO);

        Assertions.assertEquals(updatedProduct.getInventory().getQuantity(), 10);
        Assertions.assertEquals(updatedProduct.getName(), "PERFUME");
        Assertions.assertEquals(updatedProduct.getSku(), 9874);

    }

}
