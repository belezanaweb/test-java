package br.com.blz.testjava.api.domain;

public class Product {

    private Integer sku;
    private String name;
    private Inventory inventory;
    private boolean isMarketable;

    public Product() {}

    public Product(Integer sku, String name, Inventory inventory, boolean isMarketable) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
        this.isMarketable = isMarketable;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
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
        return isMarketable;
    }

    public void setMarketable(boolean marketable) {
        isMarketable = marketable;
    }
}
