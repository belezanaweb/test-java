package br.com.blz.testjava.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class InventoryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer quantity;

    @NotNull
    private List<WarehouseDTO> warehouses;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<WarehouseDTO> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseDTO> warehouses) {
        this.warehouses = warehouses;
    }
}
