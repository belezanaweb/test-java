package br.com.blz.testjava.dto;

import br.com.blz.testjava.domain.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {

    private Long sku;
    private String name;

    @JsonProperty("isMarketable")
    private boolean isMarketable;

    private InventoryDto inventory;

    public ProductDto() {

    }

    public ProductDto(Product product, InventoryDto inventoryDto) {
        this.sku = product.getSku();
        this.name = product.getName();
        this.inventory = inventoryDto;
        this.isMarketable = inventoryDto.getQuantity() > 0;
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

    public boolean isMarketable() {
        return isMarketable;
    }

    public void setMarketable(boolean marketable) {
        isMarketable = marketable;
    }

    public InventoryDto getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDto inventory) {
        this.inventory = inventory;
    }
}
