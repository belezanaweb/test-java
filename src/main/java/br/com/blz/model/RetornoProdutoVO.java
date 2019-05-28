package br.com.blz.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoProdutoVO {

	private Integer sku;
	private String name;
	private RetornoInvestarioVO inventory;
	private boolean isMarketable;
}
