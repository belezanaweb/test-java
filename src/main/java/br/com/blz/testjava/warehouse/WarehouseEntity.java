package br.com.blz.testjava.warehouse;

import br.com.blz.testjava.inventory.InventoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "warehouse")
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_warehouse", unique = true, nullable = false)
    @JsonIgnore
    private Long id;

    @JoinColumn(name = "locality", nullable = false)
    private String locality;

    @JoinColumn(name = "quantity", nullable = false)
    private Integer quantity;

    @JoinColumn(name = "type", nullable = false)
    private WarehouseType type;

}
