package br.com.blz.testjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;


import javax.persistence.*;
import java.util.List;



@Data
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonSerialize
    @Transient
    public Integer quantity() {
        return this.getWarehouses().stream().map(Warehouse::getQuantity).reduce(Integer::sum).get();
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Warehouse> warehouses;


}
