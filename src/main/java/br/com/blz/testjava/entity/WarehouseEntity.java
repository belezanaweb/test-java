package br.com.blz.testjava.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
}
