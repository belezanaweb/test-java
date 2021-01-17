package br.com.blz.testjava.model.enums;

public enum WarehouseTypes {
    ECOMMERCE("ECOMMERCE"),
    PHYSICAL_STORE("PHYSICAL_STORE");

    private String type;

    WarehouseTypes(String type){ this.type = type;}
}
