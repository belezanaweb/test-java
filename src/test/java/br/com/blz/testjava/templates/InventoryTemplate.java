package br.com.blz.testjava.templates;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.blz.testjava.dto.InventoryDTO;
import lombok.Getter;

public class InventoryTemplate extends BaseTemplate {

    @Getter private static final InventoryTemplate instance = new InventoryTemplate();

    protected final WareHouseTemplate whareHouseTemplate = WareHouseTemplate.getInstance();
    
    public InventoryDTO getValid() {
      return InventoryDTO.builder()
              .quantity(faker.options().option(56, 25))
              .warehouses(Stream.of(whareHouseTemplate.getValid(), whareHouseTemplate.getValid())
                      .collect(Collectors.toList()))
              .build();
    }
    
}
