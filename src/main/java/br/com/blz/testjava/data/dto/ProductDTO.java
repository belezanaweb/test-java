package br.com.blz.testjava.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductDTO implements Serializable {

    @NotNull(message = "Sku cannot be null")
    private String sku;
    @NotEmpty(message = "A name of product is required")
    private String name;
    @NotNull(message = "Is necessary a inventory for warehouses")
    private InventoryDTO inventory;

}
