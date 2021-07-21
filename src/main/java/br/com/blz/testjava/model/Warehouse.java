package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Warehouse {

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("quantity")
    private Long quantity;

    @JsonProperty("type")
    private String type;

    public Warehouse(String locality, Long quantity, String type) {
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
    }

    public String getLocality() { return locality; }
    public void setLocality(String value) { this.locality = value; }

    public Long getQuantity() { return quantity; }
    public void setQuantity(Long value) { this.quantity = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}
