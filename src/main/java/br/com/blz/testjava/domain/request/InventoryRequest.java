package br.com.blz.testjava.domain.request;

import java.util.List;

public class InventoryRequest {

    private List<WarehouseRequest> warehouses;

    public List<WarehouseRequest> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseRequest> warehouses) {
        this.warehouses = warehouses;
    }
}
