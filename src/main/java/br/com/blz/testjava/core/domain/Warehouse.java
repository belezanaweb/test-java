package br.com.blz.testjava.core.domain;

import br.com.blz.testjava.web.request.WarehouseDTO;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 4425565430713016014L;

    private String locality;
    private Integer quantity;
    private WarehouseType type;

    public WarehouseDTO toDto(){
        return WarehouseDTO.builder()
            .locality(locality)
            .quantity(quantity)
            .type(type)
            .build();
    }

}
