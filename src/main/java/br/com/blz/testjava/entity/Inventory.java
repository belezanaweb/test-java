package br.com.blz.testjava.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class Inventory {

    private Integer quantity;
    private List<Warehouse> warehouses;

    public Inventory(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
