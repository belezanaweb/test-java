package br.com.blz.testjava.business.model.impl;

import java.util.List;
import java.util.stream.Collectors;

import br.com.blz.testjava.business.model.InventoryBO;
import br.com.blz.testjava.business.model.WharehouseBO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Wharehouse;

public class InventoryBOImpl implements InventoryBO {

	private Integer quantity;
	private Product product;
	private List<Wharehouse> wrs;
	private List<WharehouseBO> wharehouses;
	
	
	public InventoryBOImpl(Product product, List<Wharehouse> wharehouses) {
		super();
		this.product = product;
		this.wrs = wharehouses;
		
		this.quantity = this.wrs.isEmpty() ? null : 0;
		this.wharehouses =  this.wrs.stream().map(wh -> {			
			WharehouseBOImpl wharehouseBOImpl = new WharehouseBOImpl(wh, product);
			this.quantity += wharehouseBOImpl.getQuantity();
			return wharehouseBOImpl;			
		}).collect(Collectors.toList());		
		
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public List<WharehouseBO> getWarehouses() {
		return hasWharehouses() ? this.wharehouses : null;
	}
	
	private boolean hasWharehouses() {
		return this.wharehouses != null && !this.wharehouses.isEmpty();
	}

}
