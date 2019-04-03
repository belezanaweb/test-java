package br.com.blz.testjava.rest.model.request;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.blz.testjava.business.model.InventoryBO;
import br.com.blz.testjava.business.model.WharehouseBO;
import br.com.blz.testjava.enumeration.ValidationMessageEnum;

public class InventoryRequest implements InventoryBO{
	
	@Valid
	@NotEmpty(message=ValidationMessageEnum.Constants.REQUIRED_FIELD)
	private List<WharehouseRequest> warehouses;
	
	@JsonIgnore
	@Override
	public Integer getQuantity() {
		return null;
	}

	@Override
	public List<? extends WharehouseBO> getWarehouses() {
		return this.warehouses;
	}

}
