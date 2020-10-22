package br.com.blz.testjava.model;

import br.com.blz.testjava.dto.InventoryResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class Inventory {
    private List<Warehouse> warehouses;

    public  InventoryResponseDTO toResponseDTO() {
        return InventoryResponseDTO.builder()
            .warehouses(this.getWarehouses()
                .stream()
                .map(Warehouse::toResponseDTO)
                .collect(Collectors.toList()))
            .quantity(this.getWarehouses()
                .stream().mapToInt(Warehouse::getQuantity).sum())
            .build();
    }
}
