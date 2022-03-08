package br.com.blz.testjava.dto.out;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class InventoryDTOOut {

    private int quantity;
    List<WarehouseDTOOut> dtoOutList = new ArrayList<>();

    public InventoryDTOOut(Inventory inventory) {
        this.setDtoOutList(inventory.getWarehouses());
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private void setDtoOutList(List<Warehouse> warehouses) {
        warehouses.forEach(warehouse -> this.dtoOutList.add(new WarehouseDTOOut(warehouse)));
        this.setQuantity(this.dtoOutList.stream().mapToInt(WarehouseDTOOut::getQuantity).sum());
    }

    public int getQuantity() {
        return quantity;
    }
}
