package br.com.blz.testjava.domain.response;

import java.util.List;

public class InventoryResponse {

    private List<WarehouseResponse> warehouses;

    public Integer getQuantity() {
        return getWarehouses()
                .stream()
                .map(WarehouseResponse::getQuantity)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public List<WarehouseResponse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseResponse> warehouses) {
        this.warehouses = warehouses;
    }

    public Boolean isMarketable() {
        return getQuantity() > 0;
    }

}
