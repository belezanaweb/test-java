package br.com.blz.testjava.model;

import br.com.blz.testjava.domain.enums.LocalityEnum;
import br.com.blz.testjava.domain.enums.WarehouseTypeEnum;
import br.com.blz.testjava.domain.request.WarehouseRequest;

import static java.util.Objects.isNull;

public class Warehouse {

    private LocalityEnum locality;
    private Integer quantity;
    private WarehouseTypeEnum type;

    public Warehouse() {
    }

    public Warehouse(WarehouseRequest warehouseRequest) {
        setLocality(warehouseRequest.getLocality());
        setQuantity(isNull(warehouseRequest.getQuantity()) ? 0 : warehouseRequest.getQuantity());
        setType(warehouseRequest.getType());
    }

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
