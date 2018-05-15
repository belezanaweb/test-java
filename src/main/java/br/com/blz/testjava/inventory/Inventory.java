package br.com.blz.testjava.inventory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static br.com.blz.testjava.inventory.Type.ECOMMERCE;
import static br.com.blz.testjava.inventory.Type.PHYSICAL_STORE;

@Entity
public class Inventory implements Serializable {

    @Id @GeneratedValue
    private Long id;
    @Column
    private Long sku;
    @Column
    private String name;
    @Column
    private Integer quantity;
    @Transient
    private List<Warehouse> warehouses;

    public Inventory() {
        this.warehouses = new ArrayList<>();

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setLocality("SP");
        warehouse1.setQuantity(12);
        warehouse1.setTypes(ECOMMERCE);
        warehouses.add(warehouse1);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setLocality("MOEMA");
        warehouse2.setQuantity(3);
        warehouse2.setTypes(PHYSICAL_STORE);
        warehouses.add(warehouse2);
    }

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

    public Integer getQuantity() {
        return this.warehouses.stream()
                .map(q -> q.getQuantity())
                .reduce(0, Integer::sum);
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
