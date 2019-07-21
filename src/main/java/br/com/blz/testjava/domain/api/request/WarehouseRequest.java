package br.com.blz.testjava.domain.api.request;

import br.com.blz.testjava.domain.api.request.validation.WarehouseType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WarehouseRequest {

    @NotBlank
    private String locality;
    @Min(0)
    @NotNull
    private Long quantity;
    @NotBlank
    @WarehouseType
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
        return type.toUpperCase();
    }

    public void setType(String type) {
        this.type = type;
    }
}
