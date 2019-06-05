package br.com.blz.testjava.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "warehouse")
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String locality;
    private Integer quantity;
    private String type;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventoryEntity;

    public WarehouseEntity(Long id, String locality, Integer quantity, String type) {
        this.id = id;
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
    }
}
