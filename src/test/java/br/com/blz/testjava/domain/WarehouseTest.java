package br.com.blz.testjava.domain;


import br.com.blz.testjava.dto.WarehouseDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WarehouseTest {

    @Test
    public void createWarehouseTest() {
        Product product = new Product();
        product.setSku(123456L);
        product.setName("Sample 1");

        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setLocality("SP");
        warehouseDto.setQuantity(1);
        warehouseDto.setType("PHYSICAL_STORE");

        Warehouse warehouse = new Warehouse(product, warehouseDto);

        long skuExpected = 123456L;
        String localityExpected = "SP";
        int quantityExpexted = 1;
        String typeExpected = "PHYSICAL_STORE";

        assertNotNull("Produto esta nulo", warehouse.getProduct());
        assertEquals("SKU não compatível", skuExpected, warehouse.getProduct().getSku().longValue());
        assertEquals("Localidade não compatível", localityExpected, warehouse.getLocality());
        assertEquals("Tipo não compatível", typeExpected, warehouse.getType());
        assertEquals("Quantidade não compatível", quantityExpexted, warehouse.getQuantity().intValue());
    }

}
