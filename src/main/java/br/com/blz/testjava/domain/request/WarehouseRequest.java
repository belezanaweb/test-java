package br.com.blz.testjava.domain.request;

import br.com.blz.testjava.domain.enums.LocalityEnum;
import br.com.blz.testjava.domain.enums.WarehouseTypeEnum;

import javax.validation.constraints.NotNull;

public class WarehouseRequest {

    @NotNull
    private LocalityEnum locality;
    @NotNull
    private Integer quantity;
    @NotNull
    private WarehouseTypeEnum type;

    public LocalityEnum getLocality() {
        return locality;
    }

    public void setLocality(LocalityEnum locality) {
        this.locality = locality;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public WarehouseTypeEnum getType() {
        return type;
    }

    public void setType(WarehouseTypeEnum type) {
        this.type = type;
    }
}
