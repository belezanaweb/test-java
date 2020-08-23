package br.com.blz.testjava.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductDTO {

    @NotNull
    @EqualsAndHashCode.Include
    private Long sku;

    @NotNull
    private String name;

    @Valid
    @NotNull
    private InventoryDTO inventory;


    public boolean isMarketable() {
        if (inventory == null) {
            return false;
        }
        return inventory.getWarehouses().stream()
            .anyMatch(warehouseDTO -> warehouseDTO.getQuantity() > 0);
    }

}
