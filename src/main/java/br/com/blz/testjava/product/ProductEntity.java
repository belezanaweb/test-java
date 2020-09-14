package br.com.blz.testjava.product;

import br.com.blz.testjava.inventory.InventoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product", unique = true, nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "sku", unique = true, nullable = false)
    @NotNull
    private Long sku;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_inventory")
    private InventoryEntity inventory;

    @Column(name = "isMarketable")
    private boolean isMarketable;
}
