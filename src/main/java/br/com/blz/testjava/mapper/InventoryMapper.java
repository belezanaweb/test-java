package br.com.blz.testjava.mapper;

import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.dto.WarehouseDto;
import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryMapper {

    @Autowired
    private WarehouseMapper warehouseMapper;

    public Inventory dtoToOrm(InventoryDto inventoryDto) {
        List<Warehouse> warehouses = inventoryDto.getWarehouses().stream().map(warehouse -> warehouseMapper.dtoToOrm(warehouse))
            .collect(Collectors.toList());

        return Inventory.builder()
            .warehouses(warehouses)
            .build();

    }

    public InventoryDto ormToDto(Inventory inventory) {

        List<WarehouseDto> warehousesDto = inventory.getWarehouses().stream()
            .map(warehouse -> warehouseMapper.ormToDto(warehouse))
            .collect(Collectors.toList());

        return InventoryDto.builder()
            .warehouses(warehousesDto)
            .build();

    }

}
