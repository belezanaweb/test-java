package br.com.blz.testjava.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Product {

    private Integer sku;
    private String name;
    private Inventory inventory;
    private Boolean isMarketable;

    public Product(Integer sku, String name, Inventory inventory, Boolean isMarketable) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
        this.isMarketable = isMarketable;
    }
}
