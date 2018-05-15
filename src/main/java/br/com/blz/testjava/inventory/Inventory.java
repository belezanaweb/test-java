package br.com.blz.testjava.inventory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Inventory implements Serializable {

    @Id @GeneratedValue
    private Long id;
    @Column
    private Long sku;
    @Column
    private String name;
    @Transient
    private List<Warehouse> warehouses;

    public Long getId() {
        return id;
    }

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
}
