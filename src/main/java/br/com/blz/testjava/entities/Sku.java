package br.com.blz.testjava.entities;

import java.util.List;

public class Sku {

    private Long sku;
    private String name;
    private Inventory inventory;
    private List<Warehouses> warehouses;
    private boolean isMarketable;

    public Sku() {

    }

    public Sku(Long sku, String name, Inventory inventory, List<Warehouses> warehouses, boolean isMarketable) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
        this.warehouses = warehouses;
        this.isMarketable = isMarketable;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
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

    public List<Warehouses> getWarehouses() {
        return warehouses;
    }

    public boolean getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(boolean isMarketable) {
        this.isMarketable = isMarketable;
    }

}
