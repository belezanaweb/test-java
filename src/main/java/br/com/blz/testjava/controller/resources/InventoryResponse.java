package br.com.blz.testjava.controller.resources;

import com.google.common.base.Objects;

import java.util.List;

public class InventoryResponse {
    private Integer quantity;
    private List<WarehousesResource> warehousesResource;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<WarehousesResource> getWarehousesResponse() {
        return warehousesResource;
    }

    public void setWarehousesResponse(List<WarehousesResource> warehousesResource) {
        this.warehousesResource = warehousesResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryResponse that = (InventoryResponse) o;
        return Objects.equal(quantity, that.quantity) && Objects.equal(warehousesResource, that.warehousesResource);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(quantity, warehousesResource);
    }

    @Override
    public String toString() {
        return "InventoryResponse{" +
            "quantity=" + quantity +
            ", warehousesResponse=" + warehousesResource +
            '}';
    }
}
