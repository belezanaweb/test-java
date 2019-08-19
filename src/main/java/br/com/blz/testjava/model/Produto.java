package br.com.blz.testjava.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Produto {

    @NotNull
    int sku;

    @NotNull
    String name;

    @NotNull
    Inventory inventory;

    boolean isMarketable;

    public Produto() {
    }

    public Produto(int sku, String name, Inventory inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }

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

        isMarketable = inventory.quantity > 0 ? true:false;

        return isMarketable;
    }
}
