package br.com.blz.testjava.domain.api.request;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class InventoryRequest {

    @Valid
    @NotEmpty
    private List<WarehouseRequest> warehouses;

    public List<WarehouseRequest> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseRequest> warehouses) {
        this.warehouses = warehouses;
    }
}
