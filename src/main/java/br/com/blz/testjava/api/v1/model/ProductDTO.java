package br.com.blz.testjava.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "sku")
public class ProductDTO {
	private Long sku; 
	private String name;
	private InventoryDTO inventory;
	public boolean marketable;
}