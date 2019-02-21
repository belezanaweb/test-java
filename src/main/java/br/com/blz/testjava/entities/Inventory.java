package br.com.blz.testjava.entities;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Number quantity;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Warehouse> warehouses;

}
