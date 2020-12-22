package br.com.blz.testjava.controller.resources;

import com.google.common.base.Objects;

import java.util.Set;

public class InventoryRequest {
    private Set<WarehouseResource> warehouses;

    public InventoryRequest() {}

    public InventoryRequest(Set<WarehouseResource> warehouse) {
        this.warehouses = warehouse;
    }

    public Set<WarehouseResource> getWarehouse() {
        return warehouses;
    }

    public void setWarehouse(Set<WarehouseResource> warehouse) {
        this.warehouses = warehouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryRequest that = (InventoryRequest) o;
        return Objects.equal(warehouses, that.warehouses);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(warehouses);
    }

    @Override
    public String toString() {
        return "InventoryRequest{" +
            "warehouse=" + warehouses +
            '}';
    }
}
