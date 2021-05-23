package br.com.blz.testjava.model;

import lombok.Data;

@Data
public class Warehouse {

    private String locality;

    private long quantity;

    private WarehouseType type;
}
