package br.com.blz.testjava.dto;

import br.com.blz.testjava.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class ProductRequestDTO {

    @NotNull(message = "The field sku is mandatory")
    private Long sku;

    @NotNull(message = "The field sku is mandatory")
    private String name;

    //@NotNull(message = "The inventory group is mandatory")
    private InventoryRequestDTO inventory;


    public  Product toEntity() {
        return Product.builder()
            .sku(this.getSku())
            .name(this.getName())
            .inventory(Optional.ofNullable(this.getInventory()).orElse(new InventoryRequestDTO(new ArrayList<WarehouseRequestDTO>())).toEntity())
            .build();

    }


}
