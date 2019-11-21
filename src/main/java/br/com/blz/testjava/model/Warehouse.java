/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.blz.testjava.model;

/**
 *
 * @author Zupper
 */
public class Warehouse {
    
    private String locality;
    private Integer quantity ;
    private String  type;

    public Warehouse() {
    }
    
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
