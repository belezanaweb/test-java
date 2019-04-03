package br.com.blz.testjava.business.model.impl;

import br.com.blz.testjava.business.model.WharehouseBO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Wharehouse;

public class WharehouseBOImpl implements WharehouseBO {

	private Wharehouse wharehouse;
	private Product product;
		
	public WharehouseBOImpl(Wharehouse wharehouse, Product product) {
		super();
		this.wharehouse = wharehouse;
		this.product = product;
	}

	@Override
	public String getLocality() {
		return this.wharehouse.getLocality();
	}

	@Override
	public Integer getQuantity() {
		return wharehouse.getProducts().get(this.product);
	}

	@Override
	public String getType() {
		return wharehouse.getType().toString();
	}

}
