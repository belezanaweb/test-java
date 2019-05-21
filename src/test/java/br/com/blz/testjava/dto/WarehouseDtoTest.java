package br.com.blz.testjava.dto;

import br.com.blz.testjava.domain.Warehouse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WarehouseDtoTest {

    @Test
    public void createWarehouseDtoTest() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(1);
        warehouse.setType("PHYSICAL_STORE");

        WarehouseDto warehouseDto = new WarehouseDto(warehouse);

        String localityExpected = "SP";
        int quantityExpexted = 1;
        String typeExpected = "PHYSICAL_STORE";

        assertEquals("Localidade não compatível", localityExpected, warehouseDto.getLocality());
        assertEquals("Tipo não compatível", typeExpected, warehouseDto.getType());
        assertEquals("Quantidade não compatível", quantityExpexted, warehouseDto.getQuantity().intValue());
    }

}
