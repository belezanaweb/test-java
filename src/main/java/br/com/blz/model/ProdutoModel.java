package br.com.blz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProdutoModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6675672960270644624L;
	private Integer sku;
	private String name;
	private Boolean isMarketable;
	private Long quantity;
	
	private List<DepositoModel> inventory;

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DepositoModel> getInventory() {
		if (inventory == null) {
			inventory = new ArrayList<DepositoModel>();
		}
		return inventory;
	}

	public void setInventory(List<DepositoModel> inventory) {
		this.inventory = inventory;
	}

	public Boolean getIsMarketable() {
		if (getQuantity() != null && getQuantity()>0) {
			isMarketable = Boolean.TRUE ;			
		}else {
			isMarketable = Boolean.FALSE;
		}
		return isMarketable;
	}

	public Long getQuantity() {
		quantity = 0l;
		getInventory().forEach(item->quantity+=item.getQuantity());
		return quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + ((isMarketable == null) ? 0 : isMarketable.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		ProdutoModel other = (ProdutoModel) obj;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (isMarketable == null) {
			if (other.isMarketable != null)
				return false;
		} else if (!isMarketable.equals(other.isMarketable))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}

}
