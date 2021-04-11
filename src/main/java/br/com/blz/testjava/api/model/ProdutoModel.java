package br.com.blz.testjava.api.model;

import br.com.blz.testjava.model.Inventory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {

	private Long sku;
	private String name;
	private Inventory inventory;
	private Boolean isMarketable;
}
