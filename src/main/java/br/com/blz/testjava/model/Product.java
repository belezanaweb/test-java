package br.com.blz.testjava.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@NotNull @Id
	private Long sku;
	@NotBlank
	private String description;
	@Valid @NotNull 
	private Inventory inventory;
	
	@Transient
	public boolean isMarketable() {
		return this.inventory != null && this.inventory.getQuantity() > 0;
	}
	
}
