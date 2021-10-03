package br.com.blz.testjava.model;

import java.util.List;

public class Inventory {

    private Long quantity;

    private List<Warehouse> warehouses;

    public Inventory(Long quantity, List<Warehouse> warehouses) {
        this.quantity = quantity;
        this.warehouses = warehouses;
    }

    public Long getQuantity() {

        Long quantity = 0l;

        for(Warehouse warehouse: this.warehouses){
            quantity += warehouse.getQuantity();
        }

        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
