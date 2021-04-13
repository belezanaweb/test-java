package br.com.blz.testjava.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import dto.InventoryDTO;
import dto.ProductDTO;
import dto.WarehouseDTO;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private Long sku;
	private String name;

	@OneToOne
	private Inventory inventory;

	private boolean marktable;
	
	public Product() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
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

	public ProductDTO getProductTO() {
		ProductDTO dto = new ProductDTO(this.getSku(), this.getName());

		List<InventoryWarehouse> inventoryWarehouses = this.getInventory().getWarehouses();

		List<WarehouseDTO> warehousesDto = inventoryWarehouses.stream()
				.map(inventorwarehouse -> new WarehouseDTO(inventorwarehouse.getWarehouse().getLocality(),
						inventorwarehouse.getQuantity(), inventorwarehouse.getWarehouse().getType()))
				.collect(Collectors.toList());

		dto.setInventoryDto(new InventoryDTO(warehousesDto));

		return dto;
	}

	public boolean isMarktable() {
		return marktable;
	}

	public void setMarktable(boolean marktable) {
		this.marktable = marktable;
	}
	
	
}
