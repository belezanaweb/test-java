package br.com.blz.testjava.application.dto.inventory;

import br.com.blz.testjava.application.dto.warehouse.WarehouseForm;

import java.util.List;

public class InventoryDto {

    private List<WarehouseForm> warehouses;

    private Integer quantity;

    public List<WarehouseForm> getWarehouses() {
        return warehouses;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Deprecated
    public InventoryDto() {
    }

    public InventoryDto(InventoryForm inventory) {
        this.warehouses = inventory.getWarehouses();
        this.quantity = inventory.getWarehouses().stream()
            .reduce(0, (partialQuantityResult, warehouse) -> partialQuantityResult + warehouse.getQuantity(), Integer::sum);
    }
}
