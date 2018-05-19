package br.com.blz.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.blz.enums.WarehouseTypeEnum;

public class Warehouse {
	
	@NotNull(message="A localidade é obrigatória")
	private String locality;
	@NotNull(message="A quantidade é obrigatória")
	@Digits(message="Quantidade inválida", fraction=0, integer=7)
	private Integer quantity;
	@NotNull(message="O tipo é obrigatório")
	private WarehouseTypeEnum type;
	
	public Warehouse() {
		this.quantity = 0;
	}
	
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

	@Override
	public boolean equals(Object target) {
		if (target == null) { return false; }
		if (target == this) { return true; }
		if (target.getClass() != getClass()) {
			return false;
		}
		Warehouse other = (Warehouse)target;
		return new EqualsBuilder()
				.appendSuper(super.equals(target))
				.append(this.getLocality(), other.getLocality())
				.append(this.getType(), other.getType())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).
	       append(this.getLocality()).
	       append(this.getType()).
	       toHashCode();
     }
}
