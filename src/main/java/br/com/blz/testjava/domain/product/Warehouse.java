package br.com.blz.testjava.domain.product;

import lombok.Getter;

@Getter
public class Warehouse {
    private final String locality;
    private final long quantity;
    private final String type;

    private Warehouse(String locality, long quantity, String type) {
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
    }

    public static Warehouse newWarehouse(String locality, long quantity, String type) {
        return new Warehouse(locality, quantity, type);
    }

    public static Warehouse with(String locality, long quantity, String type) {
        return new Warehouse(locality, quantity, type);
    }

    @Override
    public Warehouse clone() {
        try {
            return (Warehouse) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
