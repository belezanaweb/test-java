package br.com.blz.testjava.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private final List<Warehouse> warehouses = new ArrayList<>();

    public void add(final Warehouse item) {
        warehouses.add(item);
    }

    public void addAll(final Collection<Warehouse> items) {
        warehouses.addAll(items);
    }

    public List<Warehouse> getWarehouses() {
        return Collections.unmodifiableList(warehouses);
    }
}
