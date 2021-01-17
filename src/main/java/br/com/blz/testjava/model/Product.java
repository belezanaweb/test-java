package br.com.blz.testjava.model;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {

    private Integer sku;
    private String name;
    private Inventory inventory;
    private Boolean isMarketable;

}
