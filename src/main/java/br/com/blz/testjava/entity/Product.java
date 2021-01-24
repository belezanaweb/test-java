package br.com.blz.testjava.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Product {

    private Integer sku;
    private String name;
    private Inventory inventory;
    private boolean isMarketable;

    public Product(Integer sku, String name, Inventory inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }
}
