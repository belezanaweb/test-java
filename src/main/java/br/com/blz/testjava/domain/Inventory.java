package br.com.blz.testjava.domain;

import br.com.blz.testjava.contract.request.WarehouseRequest;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private Integer quantity;
    private List<Warehouse> warehouses = new ArrayList<>();

    private Inventory(Builder builder) {
        setQuantity(builder.quantity);
        setWarehouses(builder.warehouses);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
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
        private List<Warehouse> warehouses;

        public Builder() {
        }

        public Builder withQuantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder withWarehouses(List<Warehouse> val) {
            warehouses = val;
            return this;
        }

        public Inventory build() {
            return new Inventory(this);
        }
    }
}
