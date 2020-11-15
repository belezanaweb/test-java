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

import br.com.blz.testjava.dtos.EditProductRequest;
import br.com.blz.testjava.dtos.EditProductRequest.EditProductInventory;
import br.com.blz.testjava.dtos.Warehouse;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
class EditProductTest {

    
    @Test
    void editNonExistingProduct(@Mock ProductRepository repository) {
        Mockito.when(repository.findBySku(any(Long.class))).thenReturn(null);
        
        var productRequest = getEditProductRequestObject();
        var service = new ProductService(repository);
        Throwable ex = assertThrows(ProductNotFoundException.class, ()->service.editProduct(12L, productRequest));
        assertEquals("Produto não encontrado", ex.getMessage());
    }
    
    
    private EditProductRequest getEditProductRequestObject() {
        var productRequest = new EditProductRequest();
        productRequest.setName("batata");
        productRequest.setInventory(getEditProductInventory());
        
        return productRequest;
    }

    private EditProductInventory getEditProductInventory() {
        var inventory = new EditProductInventory();
  
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
