package br.com.blz.testjava.mapper;

import br.com.blz.testjava.dto.WarehouseDto;
import br.com.blz.testjava.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    public Warehouse dtoToOrm(WarehouseDto warehouseDto) {
        return Warehouse.builder()
            .locality(warehouseDto.getLocality())
            .quantity(warehouseDto.getQuantity())
            .type(warehouseDto.getType())
            .build();
    }

    public WarehouseDto ormToDto(Warehouse warehouse) {
        return WarehouseDto.builder()
            .locality(warehouse.getLocality())
            .quantity(warehouse.getQuantity())
            .type(warehouse.getType())
            .build();
    }

}
