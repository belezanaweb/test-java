package br.com.blz.testjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Produto {

    @Id
    @Column (name = "sku")
    private Long sku;

    @Column (name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_sku")
    private Inventory inventory;

    @Column(name = "marketable")
    private boolean marketable;

}
