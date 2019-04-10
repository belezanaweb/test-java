package br.com.blz.testjava.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InventoryWarehouseKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5722736658237802515L;

	@Column(name = "inventory_id")
	int inventoryId;

	@Column(name = "warehouse_id")
	int warehouseId;

	public InventoryWarehouseKey() {

	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + inventoryId;
		result = prime * result + warehouseId;
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
		InventoryWarehouseKey other = (InventoryWarehouseKey) obj;
		if (inventoryId != other.inventoryId)
			return false;
		if (warehouseId != other.warehouseId)
			return false;
		return true;
	}

}
