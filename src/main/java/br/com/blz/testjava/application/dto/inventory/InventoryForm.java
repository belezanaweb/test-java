package br.com.blz.testjava.application.dto.inventory;

import br.com.blz.testjava.application.dto.warehouse.WarehouseForm;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InventoryForm {

    @JsonProperty
    private List<WarehouseForm> warehouses;

    public List<WarehouseForm> getWarehouses() {
        return warehouses;
    }

    @Deprecated
    public InventoryForm() {
    }

    public InventoryForm(List<WarehouseForm> warehouses) {
        this.warehouses = warehouses;
    }
}
