package br.com.blz.testjava.api.product.dto;

import br.com.blz.testjava.api.enums.TypeWarehouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehousesDto {

  private String locality;
  private Integer quantity;
  private TypeWarehouse type;
}
