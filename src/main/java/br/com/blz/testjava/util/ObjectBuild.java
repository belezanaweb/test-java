package br.com.blz.testjava.util;

import br.com.blz.testjava.enums.WarehouseType;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

import java.util.List;

public class ObjectBuild {

    public static Product createProduct(Long id, Long sku, String name, Inventory inventory) {
        return Product.builder()
            .id(id)
            .sku(sku)
            .name(name)
            .inventory(inventory)
            .build();
    }

    public static Inventory createInventory(Long id, List<Warehouse> warehouses) {
        return Inventory.builder()
            .id(id)
            .warehouses(warehouses)
            .build();
    }

    public static Warehouse createWarehouse(Long id, String locality, WarehouseType warehouseType,
                                            Long quantity) {
        return Warehouse.builder()
            .id(id)
            .locality(locality)
            .type(warehouseType)
            .quantity(quantity)
            .build();
    }

}
