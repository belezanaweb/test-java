package br.com.blz.testjava.domain;


import lombok.Data;

@Data
public class Warehouse {

    private int id;
    private String locality;
    private int quantity;
    private PointOfServiceType type;

}
