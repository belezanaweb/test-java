package br.com.blz.testjava.product;

import br.com.blz.testjava.inventory.InventoryEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product")
public class ProductEntity {

    @Id
    @Column(name = "sku", unique = true, nullable = false)
    private Long sku;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_inventory")
    private InventoryEntity inventory;

    @Column(name = "isMarketable")
    private boolean isMarketable;
}
