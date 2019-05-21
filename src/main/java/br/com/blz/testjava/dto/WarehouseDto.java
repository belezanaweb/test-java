package br.com.blz.testjava.dto;

import br.com.blz.testjava.domain.Warehouse;

public class WarehouseDto {

    private String locality;

    private Integer quantity;

    private String type;

    public WarehouseDto() {

    }

    public WarehouseDto(Warehouse warehouse) {
        this.locality = warehouse.getLocality();
        this.quantity = warehouse.getQuantity();
        this.type = warehouse.getType();
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
