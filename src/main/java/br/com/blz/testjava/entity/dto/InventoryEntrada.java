package br.com.blz.testjava.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class InventoryEntrada {

    private List<WarehouseDTO> warehouses = new ArrayList<>();

    public InventoryEntrada() {
    }

    public List<WarehouseDTO> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseDTO> warehouses) {
        this.warehouses = warehouses;
    }
}
