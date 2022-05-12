package br.com.blz.testjava.model;

import lombok.Data;

@Data
public class WareHouse {

    private String locality;
    private int quantity;
    private WareHouseType type;

}
