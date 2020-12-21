package br.com.blz.testjava.ProductMother;

import br.com.blz.testjava.controller.resources.WarehousesResource;
import br.com.blz.testjava.enums.BrazilianStates;
import br.com.blz.testjava.enums.WarehouseType;

import static br.com.blz.testjava.enums.BrazilianStates.PERNAMBUCO;

public class WarehouseResourcesMother {

    private WarehouseResourcesMother() { }

    public static WarehousesResource createWarehouse() {
        return createWarehouse(WarehouseType.PHYSICAL_STORE, PERNAMBUCO, 10);
    }

    public static WarehousesResource createWarehouse(WarehouseType type, BrazilianStates locality, Integer quantity) {
        return new WarehousesResource(locality, quantity, type);
    }
}
