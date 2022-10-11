package br.com.blz.testjava.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WarehouseEntity {

    @NotBlank(message = "This field is required")
    private String locality;

    @NotNull(message = "This field is required")
    private Integer quantity;

    @NotBlank(message = "This field is required")
    private String type;

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
