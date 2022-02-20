package br.com.blz.testjava.domain;


import lombok.Data;

import java.util.List;

@Data
public class Inventory {

    private int id;
    private int quantity;
    private List<Warehouse> warehouses;
}
