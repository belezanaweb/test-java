package br.com.blz.testjava.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class InventoryEntity {

    @NotNull
    private List<WarehouseEntity> warehouses;

    public List<WarehouseEntity> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseEntity> warehouses) {
        this.warehouses = warehouses;
    }
}
