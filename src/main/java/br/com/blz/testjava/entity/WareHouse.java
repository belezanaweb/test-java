package br.com.blz.testjava.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class WareHouse  {

    private String locality;
    private Integer quantity;
    private WarehousesType type;
}
