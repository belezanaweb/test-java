package br.com.blz.testjava.domain;

import br.com.blz.testjava.enums.WarehouseType;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "warehouse")
public class Warehouse implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", example = "1", notes = "Identificador unico do armazém")
    private Long id;

    @Column(name = "locality")
    @ApiModelProperty(name = "locality", example = "SP", notes = "Localidade do armazém")
    private String locality;

    @Column(name = "quantity")
    @ApiModelProperty(name = "quantity", example = "12", notes = "Quantidade em estoque do armazém")
    private Long quantity;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(name = "type", example = "ECOMMERCE", notes = "Tipo do armazém")
    private WarehouseType type;


}
