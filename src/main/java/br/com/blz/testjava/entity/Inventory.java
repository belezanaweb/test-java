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
    @Column(name = "codigo")
    private long codigo;

    @JsonIgnore
    @Column(name = "locality")
    private long quantity ;

    @OneToMany(mappedBy = "inventory",  cascade = CascadeType.ALL)
    private List<Warehouses> warehouses;

    @JsonIgnore
    @OneToOne
    private Produto produto;

    @JsonIgnore
    @Column(name = "isMarketable")
    private boolean isMarketable;
}
