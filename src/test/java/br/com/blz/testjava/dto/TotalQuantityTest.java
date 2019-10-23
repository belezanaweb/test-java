package br.com.blz.testjava.dto;

import br.com.blz.testjava.controller.dto.InventoryDTO;
import br.com.blz.testjava.controller.dto.ProductDTO;
import br.com.blz.testjava.model.WarehouseType;
import br.com.blz.testjava.service.MockService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TotalQuantityTest {
    @Autowired private MockService mockService;

    @Test
    public void validate() {
        final InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.add(mockService.createWarehouse(WarehouseType.PHYSICAL_STORE, 10, "SP"));
        inventoryDTO.add(mockService.createWarehouse(WarehouseType.PHYSICAL_STORE, 11, "RJ"));
        inventoryDTO.add(mockService.createWarehouse(WarehouseType.PHYSICAL_STORE, 12, "MG"));

        assertSame(inventoryDTO.getQuantity(), 33);

        inventoryDTO.add(mockService.createWarehouse(WarehouseType.PHYSICAL_STORE, 22, "ES"));

        assertSame(inventoryDTO.getQuantity(), 55);
    }
}
