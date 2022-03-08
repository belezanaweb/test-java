package br.com.blz.testjava.entity;

import br.com.blz.testjava.dto.in.WarehouseDTOIn;

public class Warehouse {

    private String locality;
    private int quantity;
    private String type;

    public Warehouse(WarehouseDTOIn warehouseDTOIn){
        this.setLocality(warehouseDTOIn.getLocality());
        this.setQuantity(warehouseDTOIn.getQuantity());
        setType(warehouseDTOIn.getType());
    }

    public Warehouse(String locality, int quantity, String type) {
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
    }

    private void setLocality(String locality) {
        this.locality = locality;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private void setType(String type) {
        this.type = type;
    }

    public String getLocality() {
        return locality;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }
}
