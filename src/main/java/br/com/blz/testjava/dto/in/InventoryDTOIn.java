package br.com.blz.testjava.dto.in;

import java.util.ArrayList;
import java.util.List;

public class InventoryDTOIn {

    List<WarehouseDTOIn> warehouses = new ArrayList<>();

    public List<WarehouseDTOIn> getWarehouses() {
        return warehouses;
    }

    public InventoryDTOIn(){}
}
