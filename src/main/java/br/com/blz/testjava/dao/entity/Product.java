
package br.com.blz.testjava.dao.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
@Entity
public class Product implements Serializable {
	@EmbeddedId
	private ProductEntryPK sku;
    private String name;
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true, targetEntity=Inventory.class,  fetch = FetchType.EAGER)
    private Inventory inventory;
    private Boolean isMarketable;
    private final static long serialVersionUID = -2026203869071939291L;
	
    public ProductEntryPK getSku() {
		return sku;
	}
	public void setSku(ProductEntryPK sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Boolean getIsMarketable() {
		return isMarketable;
	}
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(inventory, isMarketable, name, sku);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(inventory, other.inventory) && Objects.equals(isMarketable, other.isMarketable)
				&& Objects.equals(name, other.name) && Objects.equals(sku, other.sku);
	}

}
