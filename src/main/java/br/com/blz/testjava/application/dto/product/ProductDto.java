package br.com.blz.testjava.application.dto.product;

import br.com.blz.testjava.application.dto.inventory.InventoryDto;
import br.com.blz.testjava.product.Product;

public class ProductDto {

    private Long sku;

    private String name;

    private InventoryDto inventory;

    private Boolean isMarketable;

    public Long getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public InventoryDto getInventory() {
        return inventory;
    }

    public Boolean getMarketable() {
        return isMarketable;
    }

    public ProductDto(Product product) {
        this.sku = product.getSku();
        this.name = product.getName();
        this.inventory = new InventoryDto(product.getInventory());
        this.isMarketable = inventory.getQuantity() > 0 ? true : false;
    }
}
