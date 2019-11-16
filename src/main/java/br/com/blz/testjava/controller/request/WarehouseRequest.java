package br.com.blz.testjava.controller.request;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class WarehouseRequest {
    
    @NotBlank
    private String locality;
    
    @NotNull
    private int quantity;
    
    @NotBlank
    private String type;
    
    public String getLocality() {
        return locality;
    }
    
    public void setLocality(String locality) {
        this.locality = locality;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
