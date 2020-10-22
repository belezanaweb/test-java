package br.com.blz.testjava.dto;

import br.com.blz.testjava.model.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class InventoryRequestDTO {

    private List<WarehouseRequestDTO> warehouses;

    public Inventory toEntity() {
        List<WarehouseRequestDTO> warehouses = Optional.ofNullable(this.getWarehouses())
            .orElse(new ArrayList<WarehouseRequestDTO>());

        return Inventory.builder()
            .warehouses(warehouses
                .stream()
                .map(WarehouseRequestDTO::toEntity).collect(Collectors.toList()))
            .build();
    }
}
