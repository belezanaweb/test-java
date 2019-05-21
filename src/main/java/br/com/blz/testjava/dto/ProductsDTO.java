package br.com.blz.testjava.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductsDTO {
	
	private Long sku;
	private String name;
	private boolean isMarketable;
	private InventoryDTO inventory;

}
