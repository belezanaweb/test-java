package br.com.blz.testjava.model;

import br.com.blz.testjava.enums.WarehouseType;

import lombok.Data;

@Data
public class Warehouse {

    private String locality;

    private int quantity;

    private WarehouseType type;

}
