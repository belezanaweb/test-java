package br.com.blz.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoVO {

	private Integer sku;
	private String name;
	private InventarioVO inventory;
}
