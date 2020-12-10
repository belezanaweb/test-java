package br.com.blz.testjava.controller.request;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductRequest {
    @NotNull
    private Long sku;

    @NotEmpty
    private String name;

    @NotNull
    private InventoryRequest inventory;
}
