package br.com.blz.testjava.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {
	
	private Long sku;
	private String name;
	private Inventory inventory;
	private Boolean isMarketable;

}
