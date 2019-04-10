package br.com.blz.testjava.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private int sku;
	private String name;

	@OneToOne
	private Inventory inventory;

	public Product() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
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

	public ProductTO getProductTO() {
		ProductTO productTO = new ProductTO(this.getSku(), this.getName());

		List<InventoryWarehouse> inventoryWarehouses = this.getInventory().getWarehouses();

		List<WarehouseTO> warehousesTo = inventoryWarehouses.stream()
				.map(inventorwarehouse -> new WarehouseTO(inventorwarehouse.getWarehouse().getLocality(),
						inventorwarehouse.getQuantity(), inventorwarehouse.getWarehouse().getType()))
				.collect(Collectors.toList());

		productTO.setInventoryTo(new InventoryTO(warehousesTo));

		return productTO;
	}
}
