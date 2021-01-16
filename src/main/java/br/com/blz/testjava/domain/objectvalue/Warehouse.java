package br.com.blz.testjava.domain.objectvalue;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Warehouse {

    private String locality;
    private Integer quantity;
    private WarehousesType type;
}
