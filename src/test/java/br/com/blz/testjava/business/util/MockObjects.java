package br.com.blz.testjava.business.util;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.enums.WarehouseType;

import java.util.List;

public class MockObjects {

    public static Product createProduct(String name, Long sku, Inventory inventory) {
        return Product.builder()
            .name(name)
            .sku(sku)
            .inventory(inventory)
            .build();
    }

    public static Inventory createInventory(List<Warehouse> warehouses) {
        return Inventory.builder()
            .warehouses(warehouses)
            .build();
    }

    public static Warehouse createWarehouse(String locality, WarehouseType warehouseType,
                                            Long quantity) {
        return Warehouse.builder()
            .locality(locality)
            .type(warehouseType)
            .quantity(quantity)
            .build();
    }

}
