package br.com.blz.testjava.model;

import br.com.blz.testjava.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Warehouses {

    @NotBlank(groups = {Default.class, OnUpdate.class})
	private String locality;

	@NotNull(groups = {Default.class, OnUpdate.class})
	private Integer quantity;

	@NotBlank(groups = {Default.class, OnUpdate.class})
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

 
}