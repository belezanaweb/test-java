package br.com.blz.testjava.api.product.controller.domain;

import br.com.blz.testjava.api.product.dto.InventoryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
  private Long sku;
  private String name;
  private InventoryDto inventory;
  private Boolean isMarketable;
}
