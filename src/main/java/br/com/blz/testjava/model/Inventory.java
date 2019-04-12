package br.com.blz.testjava.model;

import java.util.List;

public class Inventory {

    private Integer quantity;
    private List<Warehouse> warehouses;

    public Inventory() {
    }

    public Inventory(Integer quantity, List<Warehouse> warehouses) {
        this.quantity = quantity;
        this.warehouses = warehouses;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
