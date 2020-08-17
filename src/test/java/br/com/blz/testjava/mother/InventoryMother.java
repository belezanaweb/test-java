package br.com.blz.testjava.mother;

import br.com.blz.testjava.domain.request.InventoryRequest;
import br.com.blz.testjava.domain.request.WarehouseRequest;
import br.com.blz.testjava.domain.response.InventoryResponse;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Warehouse;

import java.util.List;

import static br.com.blz.testjava.mother.WarehouseMother.getWarehouseRequest;
import static br.com.blz.testjava.mother.WarehouseMother.getWarehouseResponse;
import static java.util.Arrays.asList;

public class InventoryMother {

    private InventoryMother() {
    }

    public static Inventory getInventory() {
        Inventory inventory = new Inventory();
        inventory.setWarehouses(asList(WarehouseMother.getWarehouse(), WarehouseMother.getWarehouse(3)));
        return inventory;
    }

    public static Inventory getInventory(List<Warehouse> warehouses) {
        Inventory inventory = getInventory();
        inventory.setWarehouses(warehouses);
        return inventory;
    }

    public static InventoryRequest getInventoryRequest() {
        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryRequest.setWarehouses(asList(getWarehouseRequest(), getWarehouseRequest()));
        return inventoryRequest;
    }

    public static InventoryRequest getInventoryRequest(List<WarehouseRequest> warehouses) {
        InventoryRequest inventoryRequest = getInventoryRequest();
        inventoryRequest.setWarehouses(warehouses);
        return inventoryRequest;
    }

    public static InventoryResponse getInventoryResponse() {
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setWarehouses(asList(getWarehouseResponse(), getWarehouseResponse()));
        return inventoryResponse;
    }
}
