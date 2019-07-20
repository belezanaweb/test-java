package br.com.blz.testjava.domain.api.response;

import br.com.blz.testjava.domain.api.request.WarehouseRequest;

import java.util.List;

public class InventoryResponse {

    private Long quantity;
    private List<WarehouseRequest> warehouses;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public List<WarehouseRequest> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseRequest> warehouses) {
        this.warehouses = warehouses;
    }
}
