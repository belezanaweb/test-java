package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.util.CollectionUtils;

import lombok.Data;

@Data
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Warehouse> warehouses;

    @SuppressWarnings("unused")
    @Transient
    public int getQuantity() {
        return CollectionUtils.isEmpty(warehouses) ? 0 : warehouses.stream().map(Warehouse::getQuantity).reduce(0, Integer::sum);
    }

}
