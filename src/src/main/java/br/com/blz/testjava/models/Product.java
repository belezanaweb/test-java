package br.com.blz.testjava.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Product {

    @Id
    private Long sku;

    @NotNull(message = "name is required")
    @NotEmpty(message = "name can't be empty")
    private String name;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "inventory")
    private Inventory inventory;

    @JsonSerialize
    @Transient
    private Boolean isMarketable(){
        return this.getInventory().quantity() > 0;
    }
}
