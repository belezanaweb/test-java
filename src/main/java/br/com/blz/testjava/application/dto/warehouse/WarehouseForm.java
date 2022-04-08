package br.com.blz.testjava.application.dto.warehouse;

public class WarehouseForm {

    private String locality;

    private Integer quantity;

    private String type;

    public String getLocality() {
        return locality;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    @Deprecated
    public WarehouseForm() {
    }

    public WarehouseForm(String locality, Integer quantity, String type) {
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
    }
}
