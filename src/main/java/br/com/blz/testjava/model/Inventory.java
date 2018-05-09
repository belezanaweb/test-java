package br.com.blz.testjava.model;

import lombok.Data;

import java.util.List;


@Data
public class Inventory {

    private long quantity;
    private List<Warehouse> warehouses;
}
