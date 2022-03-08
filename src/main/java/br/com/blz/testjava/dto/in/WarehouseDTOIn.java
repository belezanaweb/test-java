package br.com.blz.testjava.dto.in;

public class WarehouseDTOIn {

    private String locality;
    private int quantity;
    private String type;

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocality() {
        return locality;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public WarehouseDTOIn(String locality, int quantity, String type) {
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
    }


}
