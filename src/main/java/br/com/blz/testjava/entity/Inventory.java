package br.com.blz.testjava.entity;

import java.io.Serializable;
import java.util.List;

public class Inventory implements Serializable {
    
    private List<Warehouse> warehouses;
    
    public Inventory(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
    
    public Inventory() {
    }
    
    public List<Warehouse> getWarehouses() {
        return warehouses;
    }
    
    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
