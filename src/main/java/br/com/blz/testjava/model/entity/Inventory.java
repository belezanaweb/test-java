package br.com.blz.testjava.model.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Warehouse> warehouses = new HashSet<>();

    @Transient
    public Long getQuantity() {
        return !warehouses.isEmpty() ? warehouses.stream().mapToLong(Warehouse::getQuantity).sum()
            : 0L;
    }

}
