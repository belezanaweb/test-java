package br.com.blz.testjava.model;

public class Warehouse {
    private String locality;
    private Long quantity;
    private WarehouseType type;

    public Warehouse(String locality, Long quantity, WarehouseType type) {
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }
}
