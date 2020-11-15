package br.com.blz.testjava.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.blz.testjava.dtos.CreateProductRequest;
import br.com.blz.testjava.dtos.Warehouse;
import br.com.blz.testjava.dtos.CreateProductRequest.CreateProductInventory;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
class CreateProductTest {

    
    @Test
    void createDuplicatedSKU(@Mock ProductRepository repository) {
        Mockito.when(repository.findBySku(any(Long.class))).thenReturn(new Product());
        var service = new ProductService(repository);
        
        var productRequest = getCreateProductRequestObject();
        Throwable ex = assertThrows(ProductAlreadyExistsException.class, ()->service.createProduct(productRequest));
        assertEquals("Produto duplicado", ex.getMessage());
    }
    
    
    private CreateProductRequest getCreateProductRequestObject() {
        var productRequest = new CreateProductRequest();
        productRequest.setSku(12L);
        productRequest.setName("batata");
        productRequest.setInventory(getCreateProductInventory());
        
        return productRequest;
    }

    private CreateProductInventory getCreateProductInventory() {
        var inventory = new CreateProductInventory();
  
        inventory.setWarehouses(createWarehouses());
        return inventory;
    }
    

    private List<Warehouse> createWarehouses(){
        var warehouses = new ArrayList<Warehouse>();
        var warehouse1 = new Warehouse();
        warehouse1.setQuantity(2);
        var warehouse2 = new Warehouse();
        warehouse2.setQuantity(3);
        warehouses.add(warehouse1);
        warehouses.add(warehouse2);
        return warehouses;

    }
    
}
