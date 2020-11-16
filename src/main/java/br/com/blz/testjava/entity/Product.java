package br.com.blz.testjava.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Integer sku;
    private String name;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory inventory;

    public Boolean isMarketable(){
        return Optional.ofNullable(this.inventory)
            .orElse(new Inventory())
            .getQuantity() > 0;
    }
}
