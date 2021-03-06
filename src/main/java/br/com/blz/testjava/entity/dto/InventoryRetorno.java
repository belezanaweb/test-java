package br.com.blz.testjava.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class InventoryRetorno {

    private int quantity;
    private List<WarehouseDTO> warehouses = new ArrayList<>();

    public InventoryRetorno() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<WarehouseDTO> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseDTO> warehouses) {
        this.warehouses = warehouses;
    }
}
