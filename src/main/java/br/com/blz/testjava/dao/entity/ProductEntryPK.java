package br.com.blz.testjava.dao.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductEntryPK implements Serializable {

	private Long sku;

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductEntryPK other = (ProductEntryPK) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}
}
