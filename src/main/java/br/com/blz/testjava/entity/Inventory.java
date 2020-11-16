package br.com.blz.testjava.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Warehouse> warehouses;

    public Integer getQuantity(){
        return Optional.ofNullable(warehouses)
            .orElse(new ArrayList<Warehouse>())
            .stream()
            .mapToInt(Warehouse::getQuantity)
            .sum();
    }
}
