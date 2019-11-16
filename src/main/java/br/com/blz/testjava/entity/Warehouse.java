package br.com.blz.testjava.entity;

import java.io.Serializable;

public class Warehouse implements Serializable {
    
    private String locality;
    private int quantity;
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
