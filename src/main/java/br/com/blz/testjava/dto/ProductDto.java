package br.com.blz.testjava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductDto {

    private Long sku;

    private String name;

    private InventoryDto inventory;

    private Boolean isMarketable;

}
