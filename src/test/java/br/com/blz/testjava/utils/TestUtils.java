package br.com.blz.testjava.utils;

import br.com.blz.testjava.dtos.InventoryDTO;
import br.com.blz.testjava.dtos.ProductDTO;
import br.com.blz.testjava.dtos.WarehouseDTO;

import java.util.Collections;

import static br.com.blz.testjava.enums.WarehouseType.ECOMMERCE;

public abstract class TestUtils {

    public static ProductDTO buildProduct() {
        WarehouseDTO warehouseDTO = WarehouseDTO.builder()
            .locality("Locality")
            .quantity(100)
            .type(ECOMMERCE)
            .build();

        InventoryDTO inventoryDTO = InventoryDTO.builder()
            .warehouses(Collections.singletonList(warehouseDTO))
            .build();

        return ProductDTO.builder()
            .name("New Product")
            .sku(12345L)
            .inventory(inventoryDTO)
            .build();
    }
}
