package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    private long sku;

    private String name;

    private Inventory inventory;

    public Product(Long sku, String name, Inventory inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }

    public long getSku() {
        return sku;
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @JsonProperty(value= "isMarketable")
    public Boolean isMarketable() {
        return this.inventory != null && this.inventory.getQuantity() > 0;
    }

}
