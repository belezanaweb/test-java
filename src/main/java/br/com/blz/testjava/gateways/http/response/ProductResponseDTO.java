package br.com.blz.testjava.gateways.http.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private Integer sku;
    private boolean isMarketable;
    private InventoryResponseDTO inventory;
}
