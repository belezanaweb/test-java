package br.com.blz.testjava.entity.dto;

public class ProdutoRetornoDTO {

    private Long sku;
    private String name;
    private InventoryRetorno inventory = new InventoryRetorno();
    private boolean isMarketable;

    public ProdutoRetornoDTO() {
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

    public InventoryRetorno getInventory() {
        return this.inventory;
    }

    public void setInventory(InventoryRetorno inventory) {
        this.inventory = inventory;
    }

    public boolean getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(boolean isMarketable) {
        this.isMarketable = isMarketable;
    }
}
