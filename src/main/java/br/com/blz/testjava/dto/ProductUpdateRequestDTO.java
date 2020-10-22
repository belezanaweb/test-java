package br.com.blz.testjava.dto;

import br.com.blz.testjava.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ProductUpdateRequestDTO {
    @NotNull(message = "The field sku is mandatory")
    private String name;

    @NotNull(message = "The inventory group is mandatory")
    private InventoryRequestDTO inventory;

    public Product toEntity(Long sku) {

            return Product.builder()
                .sku(sku)
                .name(this.getName())
                .inventory(this.getInventory().toEntity())
                .build();


    }
}
