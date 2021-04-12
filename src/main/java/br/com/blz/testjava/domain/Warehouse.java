package br.com.blz.testjava.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Warehouse {

    @NotBlank(message = "Locality é obrigatório")
    private String locality;

    @NotNull(message = "Quantity é obrigatório")
    private Long quantity;

    @NotNull(message = "Type é obrigatório")
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
