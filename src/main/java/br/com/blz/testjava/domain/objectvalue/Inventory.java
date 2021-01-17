package br.com.blz.testjava.domain.objectvalue;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Inventory {

    private Integer quantity;
    private List<Warehouse> warehouses;

    public Inventory(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
