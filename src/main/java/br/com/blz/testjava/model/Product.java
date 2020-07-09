package br.com.blz.testjava.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Product {

    @Id
    private String sku;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Transient
    private boolean isMarketable;

    public boolean isMarketable() {
        return this.getInventory().getQuantity() > 0;
    }
}
