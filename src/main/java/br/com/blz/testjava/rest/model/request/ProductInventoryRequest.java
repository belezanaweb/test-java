package br.com.blz.testjava.rest.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.blz.testjava.business.model.InventoryBO;
import br.com.blz.testjava.business.model.ProductInventoryBO;
import br.com.blz.testjava.enumeration.ValidationMessageEnum;

public class ProductInventoryRequest implements ProductInventoryBO{

	@NotNull(message=ValidationMessageEnum.Constants.REQUIRED_FIELD)
	private long sku;
	
	@NotBlank(message=ValidationMessageEnum.Constants.REQUIRED_FIELD)
	private String name;
	
	@Valid
	@NotNull(message=ValidationMessageEnum.Constants.REQUIRED_FIELD)
	private InventoryRequest inventory; 
	
	@Override
	public Long getSku() {
		return this.sku;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public InventoryBO getInventory() {
		return this.inventory;
	}

}
