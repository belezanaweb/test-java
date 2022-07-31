package br.com.blz.testjava.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private Integer sku;
    private String name;
    private Boolean isMarketable;
    private Inventory inventory;

}
