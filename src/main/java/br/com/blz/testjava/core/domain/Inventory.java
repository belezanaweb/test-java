package br.com.blz.testjava.core.domain;

import br.com.blz.testjava.web.response.InventoryResponseDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class Inventory implements Serializable {

    private static final long serialVersionUID = 4425565430713016014L;

    private Integer quantity;
    private List<Warehouse> warehouses;

    public Integer getQuantity(){
        return warehouses.size();
    }

    public InventoryResponseDTO toDto(){
        return InventoryResponseDTO.builder()
            .quantity(getQuantity())
            .warehouses(warehouses.stream().map(
                Warehouse::toDto
                ).collect(Collectors.toList()))
            .build();
    }

}
