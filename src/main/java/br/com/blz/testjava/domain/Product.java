package br.com.blz.testjava.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @ApiModelProperty(name = "id", example = "1", notes = "Identificador unico do recurso")
    private Long id;

    @Column(name = "sky", unique = true)
    @ApiModelProperty(name = "sku", example = "43264", notes = "Identificador unico do produto no estoque")
    private Long sku;

    @Column(name = "name")
    @ApiModelProperty(name = "name", example = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", notes = "Nome do produto")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @ApiModelProperty(name = "inventory", notes = "Inventario do produto")
    private Inventory inventory;

    @ApiModelProperty(name = "marketable", example = "true", notes = "Indentifica se produto pode ser comercializado")
    @Transient
    private boolean isMarketable;

    public boolean isMarketable() {
        return Objects.nonNull(this.inventory) && Objects.nonNull(this.inventory.getQuantity()) &&
                this.inventory.getQuantity() > 0;
    }
}
