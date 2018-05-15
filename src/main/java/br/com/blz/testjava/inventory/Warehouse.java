package br.com.blz.testjava.inventory;

import java.io.Serializable;

public class Warehouse implements Serializable {

    private Long id;
    private String locality;
    private Integer quantity;
    private Enum<Type> types;

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

    public Enum<Type> getTypes() {
        return types;
    }

    public void setTypes(Enum<Type> types) {
        this.types = types;
    }
}
