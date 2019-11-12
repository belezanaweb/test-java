package br.com.blz.testjava.contract.response;

import br.com.blz.testjava.domain.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class InventoryResponse {

    private Integer quantity;
    private List<WarehouseResponse> warehouses = new ArrayList<>();

    private InventoryResponse(Builder builder) {
        setQuantity(builder.quantity);
        setWarehouses(builder.warehouses);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<WarehouseResponse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseResponse> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public String toString() {
        return "Inventory{" +
            "quantity=" + quantity +
            ", warehouses=" + warehouses +
            '}';
    }


    public static final class Builder {
        private Integer quantity;
        private List<WarehouseResponse> warehouses;

        public Builder() {
        }

        public Builder withQuantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder withWarehouses(List<WarehouseResponse> val) {
            warehouses = val;
            return this;
        }

        public InventoryResponse build() {
            return new InventoryResponse(this);
        }
    }
}
