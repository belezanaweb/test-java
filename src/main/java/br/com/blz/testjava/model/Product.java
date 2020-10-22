package br.com.blz.testjava.model;

import br.com.blz.testjava.dto.InventoryResponseDTO;
import br.com.blz.testjava.dto.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
@Builder
public class Product {
    private Long sku;
    private String name;
    private Inventory inventory;

    public ProductResponseDTO toResponseDTO() {
        InventoryResponseDTO inventory = this.getInventory().toResponseDTO();
        return ProductResponseDTO.builder()
            .sku(this.getSku())
            .name(this.getName())
            .inventory(inventory)
            .isMarketable(getIsMarketable())
            .build();
    }

    public boolean getIsMarketable() {
        Inventory inventory = this.getInventory();
        return inventory.getQuantity() > 0;
    }
}
