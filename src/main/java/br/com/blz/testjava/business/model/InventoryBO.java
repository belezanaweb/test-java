package br.com.blz.testjava.business.model;

import java.util.List;

public interface InventoryBO {

	Integer getQuantity();
	
	List<? extends WharehouseBO> getWarehouses();
	
}
