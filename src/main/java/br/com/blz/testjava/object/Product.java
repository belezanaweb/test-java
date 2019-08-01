package br.com.blz.testjava.object;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Product {

	@Id
	private long sku;
	private String name;
	
	@Transient
	private Inventory inventory;

	public Product(long sku, String name) {
		this.sku = sku;
		this.name = name;
	}

	public Product() {
	}

	public boolean isMarketable() {
		if (Objects.nonNull(inventory)) {
			return false;
		}
		return inventory.getQuantity() > 0;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public long getSku() {
		return sku;
	}

	public String getName() {
		return name;
	}

	public void setSku(long sku) {
		this.sku = sku;
	}

	@Override
	public String toString() {
		return "Produto{" + "sku=" + sku + ", name='" + name + '\'' + '}';
	}

}