package br.com.blz.testjava.model;

import lombok.Data;

@Data
public class SkuProduct {

    private long sku;
	private String name;
	private Inventory inventory;
	private boolean isMarketable;

}
