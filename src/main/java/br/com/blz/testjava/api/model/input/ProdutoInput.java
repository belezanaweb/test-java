package br.com.blz.testjava.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoInput {

	@NotNull
	private Long sku;
	@NotNull
	private String name;
	@NotNull
	private InventoryInput inventory;
}
