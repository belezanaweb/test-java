package br.com.blz.testjava.inventory;

import java.io.Serializable;

public class Warehouse implements Serializable {

    private Long id;
    private Long sku;
    private String locality;
    private Integer quantity;
    private Enum<Type> types;
}
