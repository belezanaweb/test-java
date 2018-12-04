package br.com.blz.testjava.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Inventory implements Serializable {

    private List<Warehouse> warehouses;

    public Long getQuantity() {
        if (Objects.isNull(warehouses)) {
            return 0L;
        }
        return warehouses.stream()
                .map(warehouse -> warehouse.getQuantity())
                .reduce(Long::sum)
                .orElse(0L);
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

}
