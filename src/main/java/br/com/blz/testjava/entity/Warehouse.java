package br.com.blz.testjava.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Warehouse {

    private String locality;
    private Integer quantity = 0;
    private WarehousesType type;
}