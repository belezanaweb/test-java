package br.com.blz.testjava.domain.factory;

import br.com.blz.testjava.domain.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class WarehousesFactory {
    private WarehousesFactory() {
    }

    public static List<Warehouse> zeroQuantityWarehouse() {
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(new Warehouse());

        return warehouses;
    }
}
