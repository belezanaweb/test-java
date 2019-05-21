package br.com.blz.testjava.dto;

import br.com.blz.testjava.domain.Warehouse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InventoryDtoTest {

    @Test
    public void inventoryDtoQtd1Test() {
        List<Warehouse> warehouses = new ArrayList();
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(1);
        warehouse.setType("PHYSICAL_STORE");
        warehouses.add(warehouse);

        InventoryDto inventoryDto = new InventoryDto(warehouses);

        int quantityExpected = 1;

        assertEquals("Quantidade não compatível", quantityExpected, inventoryDto.getQuantity());
    }

    @Test
    public void inventoryDtoQtd6Test() {
        List<Warehouse> warehouses = new ArrayList();
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(1);
        warehouse.setType("PHYSICAL_STORE");
        warehouses.add(warehouse);

        warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(5);
        warehouse.setType("PHYSICAL_STORE");
        warehouses.add(warehouse);

        InventoryDto inventoryDto = new InventoryDto(warehouses);

        int quantityExpected = 6;

        assertEquals("Quantidade não compatível", quantityExpected, inventoryDto.getQuantity());
    }

    @Test
    public void inventoryDtoQtd10Test() {
        List<Warehouse> warehouses = new ArrayList();
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(1);
        warehouse.setType("PHYSICAL_STORE");
        warehouses.add(warehouse);

        warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(5);
        warehouse.setType("PHYSICAL_STORE");
        warehouses.add(warehouse);

        warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(4);
        warehouse.setType("PHYSICAL_STORE");
        warehouses.add(warehouse);

        InventoryDto inventoryDto = new InventoryDto(warehouses);

        int quantityExpected = 10;

        assertEquals("Quantidade não compatível", quantityExpected, inventoryDto.getQuantity());
    }
}
