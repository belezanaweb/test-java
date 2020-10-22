package br.com.blz.testjava.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class InventoryResponseDTO {

    private Integer quantity;
    private List<WarehouseResponseDTO> warehouses;
}
