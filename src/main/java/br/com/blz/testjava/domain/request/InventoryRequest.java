package br.com.blz.testjava.domain.request;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class InventoryRequest {

    @NotEmpty
    private List<WarehouseRequest> warehouses;

    public List<WarehouseRequest> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseRequest> warehouses) {
        this.warehouses = warehouses;
    }
}
