package br.com.blz.testjava.contract.request;

import java.util.ArrayList;
import java.util.List;

public class InventoryRequest {

    private List<WarehouseRequest> warehouses = new ArrayList<>();

    private InventoryRequest(Builder builder) {
        setWarehouses(builder.warehouses);
    }


    public List<WarehouseRequest> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseRequest> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public String toString() {
        return "InventoryRequest{" +
            "warehouses=" + warehouses +
            '}';
    }


    public static final class Builder {
        private List<WarehouseRequest> warehouses;

        public Builder() {
        }

        public Builder warehouses(List<WarehouseRequest> val) {
            warehouses = val;
            return this;
        }

        public InventoryRequest build() {
            return new InventoryRequest(this);
        }
    }
}
