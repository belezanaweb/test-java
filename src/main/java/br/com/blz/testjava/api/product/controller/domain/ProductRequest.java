package br.com.blz.testjava.api.product.controller.domain;

import br.com.blz.testjava.api.product.dto.InventoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
  private Integer sku;
  private String name;
  private InventoryDto inventory;
}
