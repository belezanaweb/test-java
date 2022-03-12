package br.com.blz.testjava.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Inventory {
    private Long quantity;
    private List<Warehouse> warehouses;
}
