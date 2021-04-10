package br.com.blz.testjava.dto;

import java.util.List;

import br.com.blz.testjava.entity.Warehouse;


public class InventoryDto {

    private long quantity;

    private List<Warehouse> warehouses;

    public InventoryDto(long quantity, List<Warehouse> warehouses) {
        this.quantity = quantity;
        this.warehouses = warehouses;
    }

    public InventoryDto() {
        super();
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

}
