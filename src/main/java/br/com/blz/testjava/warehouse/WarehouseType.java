package br.com.blz.testjava.warehouse;

public enum WarehouseType {
    ECOMMERCE("ECOMMERCE"),
    PHYSICAL_STORE("PHYSICAL_STORE");

    private String type;

    WarehouseType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
