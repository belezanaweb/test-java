package br.com.blz.testjava.controller.response;

import java.util.List;

public class InventoryResponse {
    
    private int quantity;
    private List<WarehouseResponse> warehouses;
    
    public InventoryResponse(List<WarehouseResponse> warehouses, int quantity) {
        this.quantity = quantity;
        this.warehouses = warehouses;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public List<WarehouseResponse> getWarehouses() {
        return warehouses;
    }
    
    public void setWarehouses(
        List<WarehouseResponse> warehouses) {
        this.warehouses = warehouses;
    }
}
