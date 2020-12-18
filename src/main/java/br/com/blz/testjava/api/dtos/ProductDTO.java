package br.com.blz.testjava.api.dtos;

import br.com.blz.testjava.model.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    Long sku;
    String name;
    InventoryDTO inventory;
    Boolean isMarketable;

    public ProductDTO(Product product){
        this.sku = product.getSku();
        this.name = product.getName();

        List<WarehouseDTO> warehouseDtos = product.getInventory().stream()
            .map(WarehouseDTO :: new)
            .collect(Collectors.toList());

        InventoryDTO inventoryDto = new InventoryDTO();
        inventoryDto.setWarehouses(warehouseDtos);
        inventoryDto.setQuantity(0L);

        Long quantity = warehouseDtos.stream().filter(w -> w.getQuantity() > 0L).mapToLong(w -> w.getQuantity()).sum();
        inventoryDto.setQuantity(quantity);

        this.inventory = inventoryDto;

        this.isMarketable = quantity > 0;
    }
}
