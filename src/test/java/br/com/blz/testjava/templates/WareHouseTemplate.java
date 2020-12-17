package br.com.blz.testjava.templates;

import br.com.blz.testjava.dto.WarehouseDTO;
import br.com.blz.testjava.enums.WarehouseType;
import lombok.Getter;

public class WareHouseTemplate extends BaseTemplate {

    @Getter
    private static final WareHouseTemplate instance = new WareHouseTemplate();

    public WarehouseDTO getValid() {
        return WarehouseDTO.builder()
                .quantity(faker.options().option(56, 25))
                .locality(faker.options().option("SP", "MG"))
                .type(faker.options().option(WarehouseType.ECOMMERCE, 
                        WarehouseType.PHYSICAL_STORE))
                .build();
    }

}
