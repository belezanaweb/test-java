package br.com.blz.testjava.converter;

import br.com.blz.testjava.controller.dto.InventoryDTO;
import br.com.blz.testjava.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryConverter implements Converter<InventoryDTO, Inventory> {
    @Autowired private WarehouseConverter warehouseConverter;

    @Override
    public Inventory perform(final InventoryDTO input) {
        final Inventory result = new Inventory();
        result.addAll(warehouseConverter.perform(input.getWarehouses()));

        return result;
    }
}
