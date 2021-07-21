package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Inventory {

    @JsonIgnore
    @JsonProperty("quantity")
    private Long quantity;

    @JsonProperty("warehouses")
    private List<Warehouse> warehouses;

    public Inventory(Long quantity, List<Warehouse> warehouses) {
        this.quantity = quantity;
        this.warehouses = warehouses;
    }

    public Long getQuantity() { return quantity; }
    public void setQuantity(Long value) { this.quantity = value; }

    public List<Warehouse> getWarehouses() { return warehouses; }
    public void setWarehouses(List<Warehouse> value) { this.warehouses = value; }
}
