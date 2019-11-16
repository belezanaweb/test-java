package br.com.blz.testjava.controller.request;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

public class InventoryRequest {
    
    @Valid
    private List<WarehouseRequest> warehouses = new ArrayList<>();
    
    public List<WarehouseRequest> getWarehouses() {
        return warehouses;
    }
    
    public void setWarehouses(
        List<WarehouseRequest> warehouses) {
        this.warehouses = warehouses;
    }
}
