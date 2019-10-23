package br.com.blz.testjava.converter;

import br.com.blz.testjava.controller.dto.WarehouseDTO;
import br.com.blz.testjava.model.Warehouse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarehouseDtoConverter implements Converter<Warehouse, WarehouseDTO> {

    @Override
    public WarehouseDTO perform(final Warehouse input) {
        final WarehouseDTO result = new WarehouseDTO();
        result.setLocality(input.getLocality());
        result.setQuantity(input.getQuantity());
        result.setType(input.getType());

        return result;
    }
}
