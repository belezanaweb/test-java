package br.com.blz.testjava.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "product")
public class Product {

	@Id
	@Column(name="sku")
	private String SKU;
	
	@Column(nullable = false)
	private String name;	
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "sku_product")
	List<InventoryItem> inventory;
	
}
