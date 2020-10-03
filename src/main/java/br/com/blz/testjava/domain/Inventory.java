package br.com.blz.testjava.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @ApiModelProperty(name = "id", example = "1", notes = "Identificador unico do inventario")
    private Long id;

    @ApiModelProperty(name = "quantity", example = "1", notes = "Identificador a quantidade do produto no inventario")
    @Transient
    private Long quantity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id")
    @ApiModelProperty(name = "warehouses", notes = "Lista dos armazens")
    private List<Warehouse> warehouses;

    public Long getQuantity() {
        return CollectionUtils.isEmpty(this.warehouses) ? 0  : this.warehouses.stream().mapToLong(Warehouse::getQuantity).sum();
    }
}
