package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private Long id;
    private Long sku;
    private String name;
    private Inventory inventory;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isMarketable;

}
