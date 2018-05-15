package br.com.blz.testjava.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Inventory implements Serializable {

    @Id
    private Long sku;
    @Column
    private String name;
    /*@Column
    private List<Warehouse> warehouses;*/

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
