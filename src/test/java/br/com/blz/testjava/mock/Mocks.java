package br.com.blz.testjava.mock;

import br.com.blz.testjava.enums.WarehouseType;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

import java.util.List;

public class Mocks {

    public static Product createProduct(Long sku, String name, Inventory inventory) {
        return Product.builder()
            .sku(sku)
            .name(name)
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
