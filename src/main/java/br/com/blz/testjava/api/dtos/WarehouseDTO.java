package br.com.blz.testjava.api.dtos;

import br.com.blz.testjava.model.entities.enums.ProductTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {

    private Long id;
    private String locality;
    private int quantity;
    private ProductTypeEnum type;

}
