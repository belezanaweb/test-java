package br.com.blz.testjava.controller.resources;

import br.com.blz.testjava.persistence.entity.Warehouse;
import com.google.common.base.Objects;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class InventoryResponse {
    private Integer quantity;
    private List<WarehouseResource> warehouse;

    public InventoryResponse(List<Warehouse> warehouses) {
        this.quantity = warehouses.stream().map(Warehouse::getQuantity).mapToInt(Integer::intValue).sum();
        this.warehouse = warehouses.stream().map(WarehouseResource::new).collect(toList());
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<WarehouseResource> getWarehousesResponse() {
        return warehouse;
    }

    public void setWarehousesResponse(List<WarehouseResource> warehouseResource) {
        this.warehouse = warehouseResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryResponse that = (InventoryResponse) o;
        return Objects.equal(quantity, that.quantity) && Objects.equal(warehouse, that.warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(quantity, warehouse);
    }

    @Override
    public String toString() {
        return "InventoryResponse{" +
            "quantity=" + quantity +
            ", warehousesResponse=" + warehouse +
            '}';
    }
}
