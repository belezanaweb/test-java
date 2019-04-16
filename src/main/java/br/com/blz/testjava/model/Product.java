package br.com.blz.testjava.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 2342438991447216500L;
    @NotNull
    private String sku;
    @NotNull
    private String name;
    @NotNull
    private Inventory inventory;

    public Product() {
    }

    public Product(String sku, String name, Inventory inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
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

    public boolean isMarketable() {
        return inventory.getQuantity() > 0;
    }
}
