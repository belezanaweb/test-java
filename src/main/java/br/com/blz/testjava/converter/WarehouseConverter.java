package br.com.blz.testjava.converter;

import br.com.blz.testjava.controller.dto.WarehouseDTO;
import br.com.blz.testjava.model.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseConverter implements Converter<WarehouseDTO, Warehouse> {

    @Override
    public Warehouse perform(final WarehouseDTO input) {
        final Warehouse result = new Warehouse();
        result.setLocality(input.getLocality());
        result.setQuantity(input.getQuantity());
        result.setType(input.getType());

        return result;
    }
}
