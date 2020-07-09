package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Transient
    private int quantity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("inventory")
    private Set<Warehouse> warehouses = new HashSet<Warehouse>();

    public int getQuantity() {
        this.setQuantity(
            this.getWarehouses().stream()
                .filter(warehouse -> warehouse != null)
                .mapToInt(warehouse -> warehouse.getQuantity()).sum());
        return quantity;
    }
}
