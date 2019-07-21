package br.com.blz.testjava.domain.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductResponse {

    private Long sku;
    private String name;
    private InventoryResponse inventory;
    private Boolean isMarketable;
    @JsonIgnore
    private Boolean updated = false;

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

    public InventoryResponse getInventory() {
        return inventory;
    }

    public void setInventory(InventoryResponse inventory) {
        this.inventory = inventory;
    }

    public Boolean getMarketable() {
        return isMarketable;
    }

    public void setMarketable(Boolean marketable) {
        isMarketable = marketable;
    }

    public Boolean getUpdated() {
        return updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }
}
