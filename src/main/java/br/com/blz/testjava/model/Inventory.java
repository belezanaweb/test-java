package br.com.blz.testjava.model;

import br.com.blz.testjava.domain.request.InventoryRequest;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Inventory {

    private List<Warehouse> warehouses;

    public Inventory() {
    }

    public Inventory(InventoryRequest inventoryRequest) {
        setWarehouses(inventoryRequest.getWarehouses()
            .stream()
            .map(Warehouse::new)
            .collect(toList()));
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
