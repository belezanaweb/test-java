package br.com.blz.testjava.entity;

import br.com.blz.testjava.enums.WarehouseTypeEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "locality")
    private String locality;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "type")
    private WarehouseTypeEnum type;

}
