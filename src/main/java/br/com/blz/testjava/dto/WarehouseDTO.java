package br.com.blz.testjava.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.blz.testjava.enumeration.WarehouseTypeEnum;

public class WarehouseDTO {

	@NotEmpty(message = "Uma localidade do warehouse deve ser fornecida")
	String locality;

	@NotNull(message = "A quantidade de produto no warehouse deve ser especificada")
	@Min(value = 0L, message = "Um numero de produto valido deve ser especificado no warehouse")
	Integer quantity;

	@NotNull(message = "Uma localidade deve ser fornecida")
	WarehouseTypeEnum type;

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public WarehouseTypeEnum getType() {
		return type;
	}

	public void setType(WarehouseTypeEnum type) {
		this.type = type;
	}

}
