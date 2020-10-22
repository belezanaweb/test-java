package br.com.blz.testjava.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ProductResponseDTO {

    private Long sku;

    private String name;

    private InventoryResponseDTO inventory;

    private Boolean isMarketable;


}
