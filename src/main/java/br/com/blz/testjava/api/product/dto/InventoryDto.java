package br.com.blz.testjava.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {
  private Integer quantity;
  private List<WarehousesDto> warehouses;
}
