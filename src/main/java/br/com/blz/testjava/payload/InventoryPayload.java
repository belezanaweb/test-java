package br.com.blz.testjava.payload;

import java.util.List;




public class InventoryPayload {
	
	private Integer quantity;
	
	
	private List<WarehousePayload> warehouses;
	

	public InventoryPayload() {
		quantity = 0;
	}		

	
	public List<WarehousePayload> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehousePayload> warehouses) {
		this.warehouses = warehouses;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((warehouses == null) ? 0 : warehouses.hashCode());
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
		InventoryPayload other = (InventoryPayload) obj;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (warehouses == null) {
			if (other.warehouses != null)
				return false;
		} else if (!warehouses.equals(other.warehouses))
			return false;
		return true;
	}
	
	
}
