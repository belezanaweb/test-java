package br.com.blz.testjava.api.domain;

public class Warehouse {

    private String locality;
    private Integer quantity;
    private String type;

    public Warehouse() {}

    public Warehouse(String locality, Integer quantity, String type) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
