package br.com.blz.testjava.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Serializable {

    private Long id;
    private Long sku;
    private String name;
    private Integer quantity;
    private List<Warehouse> warehouses;
    private boolean marktable;

    public Integer getQuantity() {
        return this.warehouses.stream()
                .map(q -> q.getQuantity())
                .reduce(0, Integer::sum);
    }

    public boolean isMarktable() {
        Integer sum = this.warehouses.stream()
                .map(q -> q.getQuantity())
                .reduce(0, Integer::sum);
        if (sum > 0) return true;
        return marktable;
    }
}
