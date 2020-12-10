package br.com.blz.testjava.controller.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductResponse {
    private Long id;
    private Long sku;
    private String name;
    @JsonProperty("isMarketable") private boolean marketable;
    private InventoryResponse inventory;
}
