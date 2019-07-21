package br.com.blz.testjava.domain.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateProductResponse {
    private Long sku;
    private Long inventoryQuantity;
    private Boolean isMarketable;
    @JsonIgnore
    private Boolean updated = false;

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public Long getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(Long inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
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
