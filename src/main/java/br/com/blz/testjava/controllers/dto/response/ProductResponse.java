package br.com.blz.testjava.controllers.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {

    private Long sku;

    private String name;

    private InventoryResponse inventory;

}
