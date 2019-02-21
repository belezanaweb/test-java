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

    @OneToOne
    private Inventory inventory;

    private Boolean marketable;

}

