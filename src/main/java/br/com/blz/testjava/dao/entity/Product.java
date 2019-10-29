
package br.com.blz.testjava.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
@Entity
public class Product implements Serializable {
	@Id
	@NotNull
	@ApiModelProperty(required = true)
	private Long sku;
    private String name;
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true, targetEntity=Inventory.class,  fetch = FetchType.EAGER)
    private Inventory inventory;
    @Transient
    private Boolean isMarketable;
    private final static long serialVersionUID = -2026203869071939291L;
	
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
	@ApiModelProperty(required = false, hidden = true)
	public boolean isMarketable() {
		if(inventory !=null && inventory.getQuantity() > 0) {
			return this.isMarketable = true;
		}
		return this.isMarketable = false;
	}
}