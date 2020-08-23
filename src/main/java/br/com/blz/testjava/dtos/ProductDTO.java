package br.com.blz.testjava.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductDTO {

    @NotNull
    private String sku;

    @NotNull
    private String name;

    @NotNull
    private InventoryDTO inventory;


    private boolean marketable;
}
