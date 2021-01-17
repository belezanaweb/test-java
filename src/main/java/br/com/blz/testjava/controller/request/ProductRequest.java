package br.com.blz.testjava.controller.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    @Min(1)
    private Long sku;

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private InventoryRequest inventory;
}
