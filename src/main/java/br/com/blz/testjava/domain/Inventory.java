package br.com.blz.testjava.domain;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Inventory {

    @Valid
    @NotNull(message = "Warehouses é obrigatório")
    @Size(min = 1, message = "Informe pelo menos uma warehouse")
    private List<Warehouse> warehouses;

    public Long getQuantity() {
        return warehouses.stream().mapToLong(o -> o.getQuantity()).sum();
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
