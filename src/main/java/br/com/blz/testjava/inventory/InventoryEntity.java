package br.com.blz.testjava.inventory;

import br.com.blz.testjava.warehouse.WarehouseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "inventory")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_inventory", unique = true, nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id_inventory")
    private List<WarehouseEntity> warehouses;

}
