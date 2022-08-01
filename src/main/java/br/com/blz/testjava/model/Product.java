package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private Integer sku;
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isMarketable;
    private Inventory inventory;

}
