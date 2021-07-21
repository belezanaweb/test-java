package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    @JsonProperty("sku")
    private Long sku;

    @JsonProperty("name")
    private String name;

    @JsonProperty("inventory")
    private Inventory inventory;

    @JsonProperty("isMarketable")
    private Boolean isMarketable;

    public Product() {

    }

    public Product(Long sku, String name, Inventory inventory) {
        this(sku, name, inventory, false);
    }

    public Product(Long sku, String name, Inventory inventory, boolean isMarketable) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
        this.isMarketable = isMarketable;
    }

    public Long getSku() { return sku; }
    public void setSku(Long value) { this.sku = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory value) { this.inventory = value; }

    public Boolean getIsMarketable() { return isMarketable; }
    public void setIsMarketable(Boolean value) { this.isMarketable = value; }

}
