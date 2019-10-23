package br.com.blz.testjava.converter;

import br.com.blz.testjava.controller.dto.InventoryDTO;
import br.com.blz.testjava.controller.dto.WarehouseDTO;
import br.com.blz.testjava.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryDtoConverter implements Converter<Inventory, InventoryDTO> {
    @Autowired private WarehouseDtoConverter warehouseConverter;

    @Override
    public InventoryDTO perform(final Inventory input) {
        final InventoryDTO result = new InventoryDTO();
        result.addAll(warehouseConverter.perform(input.getWarehouses()));

        return result;
    }
}
