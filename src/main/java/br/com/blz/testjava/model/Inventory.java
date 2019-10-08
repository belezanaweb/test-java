package br.com.blz.testjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INVENTORIES")
public final class Inventory {

    @Id
    @GeneratedValue
    private Integer id;

    private int quantity;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Warehouse> warehouses;
}
