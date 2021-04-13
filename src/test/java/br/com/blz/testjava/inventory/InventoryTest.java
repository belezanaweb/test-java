package br.com.blz.testjava.inventory;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.blz.testjava.entity.Type;
import br.com.blz.testjava.repository.InventoryRepository;
import br.com.blz.testjava.service.ProductService;
import dto.InventoryDTO;
import dto.ProductDTO;
import dto.WarehouseDTO;

@RunWith(MockitoJUnitRunner.class)
public class InventoryTest {

    @Mock
    private InventoryRepository repository;
    @InjectMocks
    private ProductService service;

    @Test
    public void findProductBySkuSumQuantityInventory() {
        WarehouseDTO warehouse1 = new WarehouseDTO();
        
        warehouse1.setLocality("PR");
        warehouse1.setQuantity(5);
        warehouse1.setType(Type.ECOMMERCE);
        
        
        
        WarehouseDTO warehouse2 = new WarehouseDTO();
        
        warehouse2.setLocality("PR");
        warehouse2.setQuantity(3);
        warehouse2.setType(Type.PHYSICAL_STORE);

       WarehouseDTO warehouse3 = new WarehouseDTO();
        
        warehouse3.setLocality("PR");
        warehouse3.setQuantity(1);
        warehouse3.setType(Type.PHYSICAL_STORE);
        
       InventoryDTO inventory = new  InventoryDTO();
       inventory.setWarehousesDTO(new ArrayList<WarehouseDTO>()); 
       inventory.getWarehousesDTO().add(warehouse1);
       inventory.getWarehousesDTO().add(warehouse2);
       inventory.getWarehousesDTO().add(warehouse3);
               

        ProductDTO dto = new ProductDTO();
        
        dto.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
        dto.setSku(1L);
        dto.setInventoryDto(inventory);
        
        assertThat(inventory.getQuantity(), Matchers.equalTo(9));
    }

    @Test
    public void findProductBySkuMarktableTrue() {
    	  WarehouseDTO warehouse1 = new WarehouseDTO();
          
          warehouse1.setLocality("PR");
          warehouse1.setQuantity(5);
          warehouse1.setType(Type.ECOMMERCE);
          
          
          
          WarehouseDTO warehouse2 = new WarehouseDTO();
          
          warehouse2.setLocality("PR");
          warehouse2.setQuantity(3);
          warehouse2.setType(Type.PHYSICAL_STORE);

         WarehouseDTO warehouse3 = new WarehouseDTO();
          
          warehouse3.setLocality("PR");
          warehouse3.setQuantity(1);
          warehouse3.setType(Type.PHYSICAL_STORE);
          
         InventoryDTO inventory = new  InventoryDTO();
         inventory.setWarehousesDTO(new ArrayList<WarehouseDTO>()); 
         inventory.getWarehousesDTO().add(warehouse1);
         inventory.getWarehousesDTO().add(warehouse2);
         inventory.getWarehousesDTO().add(warehouse3);
                 

          ProductDTO dto = new ProductDTO();
          
          dto.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
          dto.setSku(1L);
          dto.setInventoryDto(inventory);
          
        assertThat(dto.isMarktable(), Matchers.equalTo(true));
    }


    
}