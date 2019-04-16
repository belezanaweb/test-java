package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.List;

public class Inventory implements Serializable {

    private static final long serialVersionUID = 6013562766442950067L;
    private List<Warehouse> warehouses;

    public Inventory() {
    }

    public Inventory(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Integer getQuantity() {
        Integer quantity = 0;
        for (Warehouse warehouse : warehouses) {
            quantity += warehouse.getQuantity();
        }
        return quantity;
    }
}
