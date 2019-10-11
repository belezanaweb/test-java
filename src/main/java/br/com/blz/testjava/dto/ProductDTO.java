package br.com.blz.testjava.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class ProductDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3300996921967316945L;
	private String sku;
	private String name;
	private Boolean isMarketable;
	private InventoryDTO inventory;
	public Boolean getIsMarketable() {
		if(inventory!=null && inventory.getQuantity()!=null && inventory.getQuantity()>0) {
			isMarketable = true;
		} else {
			isMarketable = false;
		}
		return isMarketable;
	}
}
