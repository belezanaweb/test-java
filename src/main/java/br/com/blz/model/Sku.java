package br.com.blz.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Sku {
	
	@NotNull(message="{application.sku.message.id.mandatory}")
	@Digits(message="{application.sku.message.id.invalid}", fraction=0, integer=18)
	@Min(value=1, message="{application.sku.message.id.invalid}")
	private Long sku;
	
	private String name;
	
	private Inventory inventory;
	
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public boolean isMarketable() {
		return this.getInventory().getQuantity() > 0;
	}
	

}
