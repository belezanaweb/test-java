package br.com.blz.testjava.dto;

import br.com.blz.testjava.enums.WarehouseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WarehouseDto {

    private String locality;

    private Integer quantity;

    private WarehouseTypeEnum type;

}
