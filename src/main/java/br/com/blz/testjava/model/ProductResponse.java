package br.com.blz.testjava.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Integer sku;
    private String name;
    private InventoryResponse inventory;
    private boolean isMarketable;
}
