package br.com.blz.testjava.domain;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

    private int sku;
    private String name;
    private Inventory inventory;

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
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
        return this.inventory.getQuantity() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return sku == product.sku;
    }

    @Override
    public int hashCode() {

        return Objects.hash(sku);
    }
}
