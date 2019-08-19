package br.com.blz.testjava.model;

import java.util.List;

public class Inventory {

    int quantity;
    List<Warehouse> warehouse;

    public Inventory() {
    }

    public Inventory(List<Warehouse> warehouse) {
        this.warehouse = warehouse;
    }

    public int getQuantity() {

        warehouse.stream().forEach(w -> quantity += w.quantity);

        return quantity;
    }

    public List<Warehouse> getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(List<Warehouse> warehouse) {
        this.warehouse = warehouse;
    }
}
