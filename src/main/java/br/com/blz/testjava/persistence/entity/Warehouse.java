package br.com.blz.testjava.persistence.entity;

import br.com.blz.testjava.controller.resources.WarehouseResource;
import br.com.blz.testjava.enums.WarehouseType;
import com.google.common.base.Objects;

public class Warehouse {
    private String locality;
    private Integer quantity;
    private WarehouseType type;

    public Warehouse() {

    }

    public Warehouse(WarehouseResource resource) {
        this.locality = resource.getLocality();
        this.quantity = resource.getQuantity();
        this.type = resource.getType();
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
        Warehouse that = (Warehouse) o;
        return locality.equals(that.locality) && Objects.equal(quantity, that.quantity) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(locality, quantity, type);
    }

    @Override
    public String toString() {
        return "Warehouses{" +
            "locality=" + locality +
            ", quantity=" + quantity +
            ", type=" + type +
            '}';
    }
}
