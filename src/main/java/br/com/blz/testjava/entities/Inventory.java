package br.com.blz.testjava.entities;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Number quantity;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Warehouse> warehouses;

    public Number getQuantity() {
        if(!CollectionUtils.isEmpty(this.warehouses))
            this.quantity = this.warehouses.stream().mapToInt(q -> q.getQuantity().intValue()).sum();
        return quantity;
    }

}
