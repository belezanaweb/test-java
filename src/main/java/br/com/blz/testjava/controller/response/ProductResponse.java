package br.com.blz.testjava.controller.response;

public class ProductResponse {
    
    private long sku;
    private String name;
    private InventoryResponse inventory;
    private boolean isMarketable;
    
    public long getSku() {
        return sku;
    }
    
    public void setSku(long sku) {
        this.sku = sku;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public InventoryResponse getInventory() {
        return inventory;
    }
    
    public void setInventory(InventoryResponse inventory) {
        this.inventory = inventory;
    }
    
    public boolean getIsMarketable() {
        return isMarketable;
    }
    
    public void setMarketable(boolean marketable) {
        isMarketable = marketable;
    }
}
