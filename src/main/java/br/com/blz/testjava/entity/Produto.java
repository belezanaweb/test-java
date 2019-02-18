package br.com.blz.testjava.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Produto {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column (name = "sku")
    private Long sku;

    @Column (name = "name")
    private String name;

    @OneToOne(mappedBy = "produto",  cascade = CascadeType.ALL)
    private Inventory inventory;
}
