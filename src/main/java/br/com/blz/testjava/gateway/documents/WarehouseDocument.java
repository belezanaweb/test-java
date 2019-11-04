package br.com.blz.testjava.gateway.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDocument {
    private String locality; //Deveria ser um Enum?
    private Integer quantity;
    private WarehouseTypeDocument type;
}
