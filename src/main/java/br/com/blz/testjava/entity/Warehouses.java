package br.com.blz.testjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Warehouses {

    @Id
    @GeneratedValue
    @JsonIgnore
    @Column(name = "codigo")
    private long codigo;

    @Column(name = "locality")
    private String locality;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @ManyToOne
    private Inventory inventory;

}
