package br.com.blz.testjava.model;

import br.com.blz.testjava.enums.WarehouseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Warehouse {
    private String locality;
    private Long quantity;
    private WarehouseType type;
}
