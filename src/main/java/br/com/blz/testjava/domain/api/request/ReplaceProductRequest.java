package br.com.blz.testjava.domain.api.request;

import java.util.List;

public class ReplaceProductRequest {

    private String name;
    private List<WarehouseRequest> warehouses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WarehouseRequest> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseRequest> warehouses) {
        this.warehouses = warehouses;
    }
}
