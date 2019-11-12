package br.com.blz.testjava.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 8952717027286963613L;

	private Long sku;

	private String name;

	private InventoryDto inventory;

	private Boolean isMarketable;
	
	public ProductDto() {}

}
