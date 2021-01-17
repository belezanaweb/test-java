package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Inventory {
    private Integer quantity;
    private List<Warehouse> warehouses;
}
