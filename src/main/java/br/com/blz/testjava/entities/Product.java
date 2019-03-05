package br.com.blz.testjava.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private Number sku;

    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Inventory inventory;

    @Transient
    private Boolean isMarketable;

    public Boolean getIsMarketable() {
        if (this.inventory != null) {
            this.isMarketable = (this.inventory.getQuantity().intValue() > 0);
        }
        return isMarketable;
    }

}

