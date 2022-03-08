package br.com.blz.testjava.dto.out;

import br.com.blz.testjava.entity.Warehouse;

public class WarehouseDTOOut {

    private String locality;
    private Integer quantity;
    private String type;

    public WarehouseDTOOut(Warehouse warehouse) {
        this.setLocality(warehouse.getLocality());
        this.setQuantity(warehouse.getQuantity());
        this.setType(warehouse.getType());
    }

    public String getLocality() {
        return locality;
    }

    public String getType() {
        return type;
    }

    private void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    private void setType(String type) {
        this.type = type;
    }

    private void setLocality(String locality) {
        this.locality = locality;
    }

    public int getQuantity() {
        return quantity;
    }
}
