package br.com.blz.testjava.model;

import br.com.blz.testjava.dto.WarehouseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class Warehouse {

    private String locality;
    private Integer quantity;
    private String type;

    public WarehouseResponseDTO toResponseDTO() {
        return WarehouseResponseDTO.builder()
            .locality(this.getLocality())
            .quantity(this.getQuantity())
            .type(this.getType())
            .build();
    }
}
