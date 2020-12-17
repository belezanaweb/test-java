package br.com.blz.testjava.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private Long sku;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;

    @Transient
    public Boolean getIsMarketable() {
        return inventory != null && inventory.getQuantity() > 0;

    }


}
