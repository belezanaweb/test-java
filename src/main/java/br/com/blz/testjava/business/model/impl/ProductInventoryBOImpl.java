package br.com.blz.testjava.business.model.impl;

import java.util.List;

import br.com.blz.testjava.business.model.InventoryBO;
import br.com.blz.testjava.business.model.ProductInventoryBO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Wharehouse;

public class ProductInventoryBOImpl implements ProductInventoryBO {

	private Product product;
	
	private List<Wharehouse> wharehouses;
	
	public ProductInventoryBOImpl(Product product, List<Wharehouse> wharehouses) {
		super();
		this.product = product;
		this.wharehouses = wharehouses;
	}

	@Override
	public Long getSku() {
		return this.product.getSku();
	}

	@Override
	public String getName() {
		return this.product.getName();
	}

	@Override
	public InventoryBO getInventory() {
		return hasWharehouses()
			? new InventoryBOImpl(this.product, this.wharehouses)
			: null;
	}
	
	private boolean hasWharehouses() {
		return this.wharehouses != null && !this.wharehouses.isEmpty();
	}

}
