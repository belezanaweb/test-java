package br.com.blz.testjava.model;

import br.com.blz.testjava.model.enums.WarehouseTypes;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Warehouse {
    private String locality;
    private Integer quantity;
    private WarehouseTypes type;
}
