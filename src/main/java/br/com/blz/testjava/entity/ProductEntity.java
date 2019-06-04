package br.com.blz.testjava.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sku;
    private String name;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    @Column(name = "is_marketable")
    private boolean isMarketable;

}
