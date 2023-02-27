package br.com.blz.testjava.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Product {

    @NotNull(message = "The sku property is mandatory")
    private Long sku;

    @NotEmpty(message = "The name property is mandatory")
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isMarketable;
    private Inventory inventory;

    public Product(Long sku, String name, Inventory inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }

    public Long getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsMarketable() {
        return this.inventory.getQuantity() > 0;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return sku.equals(product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

}
