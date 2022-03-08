package br.com.blz.testjava.dto.out;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class InventoryDTOOut {

    private int quantity;
    List<WarehouseDTOOut> warehouses = new ArrayList<>();

    public InventoryDTOOut(Inventory inventory) {
        this.setWarehouses(inventory.getWarehouses());
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private void setWarehouses(List<Warehouse> warehouses) {
        warehouses.forEach(warehouse -> this.warehouses.add(new WarehouseDTOOut(warehouse)));
        this.setQuantity(this.warehouses.stream().mapToInt(WarehouseDTOOut::getQuantity).sum());
    }

    public int getQuantity() {
        return quantity;
    }

    public List<WarehouseDTOOut> getWarehouses() {
        return warehouses;
    }
}
