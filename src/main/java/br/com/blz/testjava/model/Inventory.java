package br.com.blz.testjava.model;

import java.util.List;

public class Inventory {
    private Long quantity;
    private List<Warehouse> warehouses;

    public Inventory(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Long getQuantity() {
        return this.warehouses
            .stream()
            .map(w -> w.getQuantity())
            .reduce(0L, (num1, num2) -> (num1 + num2));
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
