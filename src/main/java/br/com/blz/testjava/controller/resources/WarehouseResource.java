package br.com.blz.testjava.controller.resources;

import br.com.blz.testjava.enums.WarehouseType;
import br.com.blz.testjava.persistence.entity.Warehouse;
import com.google.common.base.Objects;

public class WarehouseResource {
    private String locality;
    private Integer quantity;
    private WarehouseType type;

    public WarehouseResource() {
    }

    public WarehouseResource(String locality, Integer quantity, WarehouseType type) {
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
    }

    public WarehouseResource(Warehouse warehouse) {
        this.locality = warehouse.getLocality();
        this.quantity = warehouse.getQuantity();
        this.type = warehouse.getType();
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseResource that = (WarehouseResource) o;
        return locality.equals(that.locality) && Objects.equal(quantity, that.quantity) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(locality, quantity, type);
    }

    @Override
    public String toString() {
        return "WarehousesResponse{" +
            "locality=" + locality +
            ", quantity=" + quantity +
            ", type=" + type +
            '}';
    }
}
