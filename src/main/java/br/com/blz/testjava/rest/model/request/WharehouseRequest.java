package br.com.blz.testjava.rest.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.blz.testjava.business.model.WharehouseBO;
import br.com.blz.testjava.enumeration.ValidationMessageEnum;

public class WharehouseRequest implements WharehouseBO {

	@NotEmpty(message=ValidationMessageEnum.Constants.REQUIRED_FIELD)
	private String locality;
	
	@NotNull(message=ValidationMessageEnum.Constants.REQUIRED_FIELD)
	private Integer quantity;
	
	@Pattern(regexp = "ECOMERCE|PHYSICAL_STORE",message=ValidationMessageEnum.Constants.INVALID_WHAREHOUSE_TYPE)
	@NotEmpty(message=ValidationMessageEnum.Constants.REQUIRED_FIELD)
	private String type;
	
	@Override
	public String getLocality() {
		return this.locality;
	}

	@Override
	public Integer getQuantity() {
		return this.quantity;
	}

	@Override
	public String getType() {
		return this.type;
	}
		
}
