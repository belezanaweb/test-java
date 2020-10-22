package br.com.blz.testjava.dto;

import br.com.blz.testjava.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class WarehouseRequestDTO {

    private String locality;
    private Integer quantity;
    private String type;


    public Warehouse toEntity() {
        return Warehouse.builder()
            .locality(this.getLocality())
            .quantity(this.getQuantity())
            .type(this.getType())
            .build();
    }
}
