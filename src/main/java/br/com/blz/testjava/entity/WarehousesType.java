package br.com.blz.testjava.entity;

public enum WarehousesType {
	
    ECOMMERCE("ECOMMERCE"),
    PHYSICAL_STORE("PHYSICAL_STORE");

    private String type;

    WarehousesType(String type){ this.type = type;}
}