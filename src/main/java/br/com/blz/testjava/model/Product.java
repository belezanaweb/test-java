package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private Long id;
    private Long sku;
    private String name;
    private Inventory inventory;

    @JsonIgnore
    private boolean isMarketable;

}
