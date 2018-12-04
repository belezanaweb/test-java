package br.com.blz.testjava.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Product implements Serializable {

    @Id
    private Long sku;

    private String name;

    @Transient
    private Inventory inventory;

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean isIsMarketable() {
        return Objects.nonNull(this.getInventory())
                && this.getInventory().getQuantity() > 0;

    }

}
