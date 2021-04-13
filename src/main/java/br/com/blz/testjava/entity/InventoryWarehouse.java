package br.com.blz.testjava.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class InventoryWarehouse {

	@EmbeddedId
	InventoryWarehouseKey id;

	@ManyToOne
	@MapsId("inventory_id")
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;

	@ManyToOne
	@MapsId("warehouse_id")
	@JoinColumn(name = "warehouse_id")
	private Warehouse warehouse;

	private int quantity;

	public InventoryWarehouse() {

	}

	public InventoryWarehouse(InventoryWarehouseKey id, Inventory inventory, Warehouse warehouse, int quantity) {
		this.id = id;
		this.inventory = inventory;
		this.warehouse = warehouse;
		this.quantity = quantity;
	}

	public InventoryWarehouseKey getId() {
		return id;
	}

	public void setId(InventoryWarehouseKey id) {
		this.id = id;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
