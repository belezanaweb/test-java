package br.com.blz.testjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Inventory {

    @Id
    @GeneratedValue
    @JsonIgnore
    @Column(name = "codigoi")
    private long codigoI;

    @Column(name = "locality")
    private long quantity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_codigoi")
    private List<Warehouses> warehouses;

    @JsonIgnore
    @OneToOne
    private Produto produto;
}
