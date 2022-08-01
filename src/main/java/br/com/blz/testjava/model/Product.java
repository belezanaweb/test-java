package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    @NotNull
    private Integer sku;

    @NotNull
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isMarketable;

    @NotNull
    private Inventory inventory;

}
