package br.com.blz.testjava.controller.resources;

import br.com.blz.testjava.enums.BrazilianStates;
import br.com.blz.testjava.enums.WarehouseType;
import com.google.common.base.Objects;

public class WarehousesResource {
    private BrazilianStates locality;
    private Integer quantity;
    private WarehouseType type;

    public WarehousesResource() {
    }

    public WarehousesResource(BrazilianStates locality, Integer quantity, WarehouseType type) {
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
    }

    public BrazilianStates getLocality() {
        return locality;
    }

    public void setLocality(BrazilianStates locality) {
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
        WarehousesResource that = (WarehousesResource) o;
        return locality == that.locality && Objects.equal(quantity, that.quantity) && type == that.type;
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
