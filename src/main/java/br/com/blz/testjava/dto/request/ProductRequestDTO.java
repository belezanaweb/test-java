package br.com.blz.testjava.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ProductRequestDTO {

    @NotNull(message = "{validator.product.sku.notnull}")
    @JsonProperty("sku")
    private Integer sku;

    @NotNull(message = "{validator.product.name.notnull}")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "{validator.product.inventory.notnull}")
    @Valid
    @JsonProperty("inventory")
    private InventoryDTO inventory;
}
