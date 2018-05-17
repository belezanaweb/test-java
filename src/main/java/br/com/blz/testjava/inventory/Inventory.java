package br.com.blz.testjava.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory implements Serializable {

    @Id @GeneratedValue
    private Long id;
    private Long sku;
    private String name;
    private Integer quantity;
    @Transient
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
