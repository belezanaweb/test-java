package br.com.blz.testjava.model;

import br.com.blz.testjava.enums.Type;

import javax.validation.constraints.NotNull;

public class Warehouse {

    @NotNull
    String locality;

    @NotNull
    int quantity;

    @NotNull
    Type type;

    public Warehouse() {
    }

    public Warehouse(String locality, int quantity, Type type) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
