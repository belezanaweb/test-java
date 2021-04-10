package br.com.blz.testjava.dto;


public class ProductDto {

    private String sku;
    private String name;
    private boolean isMarketable;

    private InventoryDto inventory;


    public ProductDto(String sku, String name, InventoryDto inventory, boolean isMarketable) {
        super();
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
        this.isMarketable = isMarketable;
    }

    public ProductDto() {
        super();
    }

    public InventoryDto getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDto inventory) {
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

    public boolean isMarketable() {
        return isMarketable;
    }

    public void setMarketable(boolean isMarketable) {
        this.isMarketable = isMarketable;
    }
}
