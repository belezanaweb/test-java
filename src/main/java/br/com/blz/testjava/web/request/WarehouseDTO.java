package br.com.blz.testjava.web.request;

import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.domain.WarehouseType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {

    @NotBlank(message = "Field warehouses could not be null/empty.")
    private String locality;

    @NotNull(message = "Field quantity could not be null.")
    private Integer quantity;

    @NotNull(message = "Field quantity could not be null.")
    private WarehouseType type;

    public Warehouse toDomain(){
        return Warehouse.builder()
            .locality(locality)
            .quantity(quantity)
            .type(type)
            .build();
    }

    public WarehouseDTO from(Warehouse warehouse){
        return WarehouseDTO.builder()
            .locality(warehouse.getLocality())
            .quantity(warehouse.getQuantity())
            .type(warehouse.getType())
            .build();
    }

}
