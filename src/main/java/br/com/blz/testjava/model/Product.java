package br.com.blz.testjava.model;

import lombok.Data;


@Data
public class Product {

    private Integer sku;
    private String name;
    private Inventory inventory;
}
