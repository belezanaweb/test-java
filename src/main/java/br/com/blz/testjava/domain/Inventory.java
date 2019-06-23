package br.com.blz.testjava.domain;

import java.util.List;

public class Inventory {

    private List<Warehouse> warehouses;

    public Inventory(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Inventory() {
    }

    public int getQuantity() {
        int quantity = 0;
        if (warehouses == null || warehouses.isEmpty()) {
            return quantity;
        }
        for (Warehouse warehouse : warehouses) {
            quantity += warehouse.getQuantity();
        }
        return quantity;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }
}
