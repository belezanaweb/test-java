package br.com.blz.testjava.web.response;

import br.com.blz.testjava.core.domain.Inventory;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.web.request.WarehouseDTO;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseDTO {

    private List<WarehouseDTO> warehouses;
    private Integer quantity;

    public InventoryResponseDTO from(Inventory inventory){
        return InventoryResponseDTO.builder()
            .quantity(inventory.getQuantity())
            .warehouses(inventory.getWarehouses().stream().map(
                Warehouse::toDto
            ).collect(Collectors.toList()))
            .build();
    }

}
