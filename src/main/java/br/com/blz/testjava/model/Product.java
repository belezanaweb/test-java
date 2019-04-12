package br.com.blz.testjava.model;

import java.io.Serializable;

public class Product implements Serializable {

    private String sku;
    private String name;
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
}
