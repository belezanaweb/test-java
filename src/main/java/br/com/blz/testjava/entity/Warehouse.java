package br.com.blz.testjava.entity;

import br.com.blz.testjava.enums.WarehouseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    
    private Integer quantity;
    private String locality;
    private WarehouseType type;
    
}
