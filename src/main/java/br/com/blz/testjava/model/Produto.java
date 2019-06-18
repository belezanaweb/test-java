package br.com.blz.testjava.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.minidev.json.JSONObject;
/**
 * @author Evandro Lopes da Rocha (evandro.esw@gmail.com)
 * @date 13/06/2019.
 */
@Entity
@Table(name="produto")
public class Produto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1878169837578708501L;

	private long id;
	private long sku;
	private String name;
	private Inventory inventory;

	private boolean isMarketable;
	
	public Produto(){
	};
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="sku")
	public long getSku() {
		return sku;
	}

	public void setSku(long sku) {
		this.sku = sku;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	@Column(name="is_marketable")
	public boolean isMarketable() {
		return isMarketable;
	}

	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

}