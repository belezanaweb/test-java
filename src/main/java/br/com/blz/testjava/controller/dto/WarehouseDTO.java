package br.com.blz.testjava.controller.dto;

import br.com.blz.testjava.model.WarehouseType;

public class WarehouseDTO {
    private String locality;
    private int quantity;
    private WarehouseType type;

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }
}
