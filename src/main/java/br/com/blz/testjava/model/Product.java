package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sku",
    "name",
    "inventory",
    "isMarketable"
})
public class Product {

    @JsonProperty("sku")
    private Long sku;
    @JsonProperty("name")
    private String name;
    @JsonProperty(value = "inventory", required = true)
    private Inventory inventory;
    @JsonProperty("isMarketable")
    private Boolean isMarketable;

    @JsonProperty("sku")
    public Long getSku() {
        return sku;
    }

    @JsonProperty("sku")
    public void setSku(Long sku) {
        this.sku = sku;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("inventory")
    public Inventory getInventory() {
        return inventory;
    }

    @JsonProperty("inventory")
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @JsonProperty("isMarketable")
    public Boolean getIsMarketable() {
        return isMarketable;
    }

    @JsonProperty("isMarketable")
    public void setIsMarketable(Boolean isMarketable) {
        this.isMarketable = isMarketable;
    }
}
