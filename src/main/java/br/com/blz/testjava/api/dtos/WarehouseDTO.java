package br.com.blz.testjava.api.dtos;

import br.com.blz.testjava.model.enums.TypeWarehouseEnum;
import br.com.blz.testjava.model.entities.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {
    String locality;
    Long quantity;
    TypeWarehouseEnum type;

    public WarehouseDTO(Warehouse warehouse){
        this.locality = warehouse.getLocality().getName();
        this.quantity = warehouse.getQuantity();
        this.type = warehouse.getType();
    }
}
