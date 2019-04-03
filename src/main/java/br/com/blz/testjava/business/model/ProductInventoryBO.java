package br.com.blz.testjava.business.model;

public interface ProductInventoryBO {

	Long getSku();
	
	String getName();
	
	InventoryBO getInventory();
	
}
