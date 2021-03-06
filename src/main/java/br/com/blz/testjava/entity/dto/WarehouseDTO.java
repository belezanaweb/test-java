package br.com.blz.testjava.entity.dto;

public class WarehouseDTO {

    private String locality;
    private String type;
    private int quantity;

    public WarehouseDTO(String locality, String type, int quantity) {
        this.locality = locality;
        this.type = type;
        this.quantity = quantity;
    }

    public WarehouseDTO() {

    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
