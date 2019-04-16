package br.com.blz.testjava.model;

import java.io.Serializable;

public class Warehouse implements Serializable {
    private static final long serialVersionUID = -2604555741588856459L;
    private String locality;
    private Integer quantity;
    private WarehouseType type;

    public Warehouse() {
    }

    public Warehouse(String locality, Integer quantity, WarehouseType type) {
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
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

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }
}
