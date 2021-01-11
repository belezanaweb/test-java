package br.com.blz.testjava.model;

import java.math.BigDecimal;

import com.testeSpring.model.WareHouseEnum;

public class WareHouse {
	
	private String locality;
	private BigDecimal quantity;
	private WareHouseEnum Type;
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public WareHouseEnum getType() {
		return Type;
	}
	public void setType(WareHouseEnum type) {
		Type = type;
	}

}
