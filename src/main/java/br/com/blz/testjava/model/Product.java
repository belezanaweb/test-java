package br.com.blz.testjava.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long Sku;
    private String name;
    private Inventory inventory;
    private Boolean isMarketable;
}
