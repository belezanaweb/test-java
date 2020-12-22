package br.com.blz.testjava.mother;

import br.com.blz.testjava.controller.resources.WarehouseResource;
import br.com.blz.testjava.enums.WarehouseType;


public class WarehouseResourcesMother {

    private WarehouseResourcesMother() { }

    public static WarehouseResource createWarehouse() {
        return createWarehouse(WarehouseType.PHYSICAL_STORE, "Recife", 10);
    }

    public static WarehouseResource createWarehouse(WarehouseType type, String locality, Integer quantity) {
        return new WarehouseResource(locality, quantity, type);
    }
}
