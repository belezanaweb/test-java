package br.com.blz.testjava.domain;

import java.util.List;

public class Inventory {

    private List<Warehouse> warehouses;

    public int getQuantity() {
        return warehouses.stream().mapToInt(Warehouse::getQuantity).sum();
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }
}
