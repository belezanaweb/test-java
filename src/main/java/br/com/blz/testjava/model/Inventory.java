/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.blz.testjava.model;

import java.util.List;

/**
 *
 * @author Zupper
 */
public class Inventory {
    
    private Integer quantity;
    private List<Warehouse> warehouses;

    public Inventory() {
    }

    public Integer getQuantity() {
        return quantity == null ? 0 : quantity;
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
    
    
}
