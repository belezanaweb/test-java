package br.com.blz.testjava.inventory;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Type;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.repository.InventoryRepository;
import br.com.blz.testjava.service.InventoryService;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceTest {

    @Mock
    private InventoryRepository repository;
    @InjectMocks
    private InventoryService service;

    @Test
    public void findProductBySkuSumQuantityInventory() {
        Warehouse warehouse1 = Warehouse.builder()
                .locality("PR")
                .quantity(5)
                .types(Type.ECOMMERCE)
                .build();

        Warehouse warehouse2 = Warehouse.builder()
                .locality("PR")
                .quantity(3)
                .types(Type.PHYSICAL_STORE)
                .build();

        Warehouse warehouse3 = Warehouse.builder()
                .locality("PR")
                .quantity(1)
                .types(Type.PHYSICAL_STORE)
                .build();
        
        Inventory inventory1 = Inventory.builder()
                .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
                .sku(1L)
                .warehouses(Arrays.asList(warehouse1, warehouse2, warehouse3))
                .build();

        Mockito.when(service.findByProduct(Mockito.anyLong())).thenReturn(inventory1);
        Inventory inventory = service.findByProduct(2L);
        assertThat(inventory.getQuantity(), Matchers.equalTo(9));
    }

    @Test
    public void findProductBySkuMarktableTrue() {
        Warehouse warehouse1 = Warehouse.builder()
                .locality("SP")
                .quantity(12)
                .types(Type.ECOMMERCE)
                .build();

        Warehouse warehouse2 = Warehouse.builder()
                .locality("SP")
                .quantity(3)
                .types(Type.PHYSICAL_STORE)
                .build();

        Inventory inventory1 = Inventory.builder()
                .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
                .sku(1L)
                .warehouses(Arrays.asList(warehouse1, warehouse2))
                .build();
        Mockito.when(service.findByProduct(Mockito.anyLong())).thenReturn(inventory1);
        Inventory inventory = service.findByProduct(2L);
        assertThat(inventory.isMarktable(), Matchers.equalTo(true));
    }
    
    
    @Test
    public void saveNewProduct() {
    	
    	Random random = new Random();
    	Inventory inventory = Inventory.builder().name("Novo Produto").sku(random.nextLong()).build();
    	
    	   Mockito.when(service.findByProduct(Mockito.anyLong())).thenReturn(inventory);
           Inventory inventory2 = service.findByProduct(inventory.getSku());
           assertEquals(inventory2.getSku(),inventory.getSku());
    }
    
    
    
    @Test
    public void alterProduct() {
    	
    	
        Warehouse warehouse1 = Warehouse.builder()
                .locality("SP")
                .quantity(12)
                .types(Type.ECOMMERCE)
                .build();

        Warehouse warehouse2 = Warehouse.builder()
                .locality("SP")
                .quantity(3)
                .types(Type.PHYSICAL_STORE)
                .build();

        Inventory inventory1 = Inventory.builder()
                .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
                .sku(2L)
                .warehouses(Arrays.asList(warehouse1, warehouse2))
                .build();
        Mockito.when(service.findByProduct(Mockito.anyLong())).thenReturn(inventory1);
        
        
        Inventory inventoryBase = service.findByProduct(2L);
         inventoryBase.setName("Teste");
         Mockito.when(service.findByProduct(Mockito.anyLong())).thenReturn(inventoryBase);
        
        service.save(inventoryBase);
    	   
    	  
    	   
    	   assertNotEquals(inventoryBase.getName(), "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");   	   
    	   
    	   
    }
    
    
    @Test
    public void deleteProduct() {
    	
    	Inventory inventory1 = Inventory.builder().id(1L)
                .name("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g")
                .sku(2L)
                .warehouses(null)
                .build();
 	   service.save(inventory1);
  	   service.deleteInventory(inventory1.getId());
    	System.out.println("1");  
    }
    
    
    

    
}