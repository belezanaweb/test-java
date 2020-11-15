package br.com.blz.testjava.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	@Column(unique = true)
	private Long sku;
	@Column
	private String name;
	@ManyToOne
	@Cascade(CascadeType.ALL)
	private Inventory inventory;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	
	public Boolean getIsMarketable() {
	    if(this.inventory == null)
	        return false;
	    
		return  this.inventory.getQuantity() >0;
	}
	
}
