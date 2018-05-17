package br.com.blz.testjava.models;

import java.util.List;

public class Inventory {

    private Integer quantity;
    private List<Warehouse> warehouses;

    public Inventory() {
    }

    public Inventory(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Integer calculateQuantity() {
        if (warehouses != null && !warehouses.isEmpty()) {
            return warehouses.stream()
                    .mapToInt(Warehouse::getQuantity)
                    .sum();
        }
        return 0;
    }
}
