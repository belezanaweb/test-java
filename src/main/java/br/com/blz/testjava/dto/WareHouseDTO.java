package br.com.blz.testjava.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.model.WareHouseEnum;

@Component
public class WareHouseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String locality;
	private BigDecimal quantity;
	private WareHouseEnum type;
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
		return type;
	}
	public void setType(WareHouseEnum type) {
		this.type = type;
	}
	
}
