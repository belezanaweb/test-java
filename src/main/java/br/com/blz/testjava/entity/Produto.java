package br.com.blz.testjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Produto {

    @Id
    @Column (name = "sku")
    private Long sku;

    @Column (name = "name")
    private String name;

    @OneToOne(mappedBy = "produto",  cascade = CascadeType.ALL)
    private Inventory inventory;

    @Column(name = "isMarketable")
    private boolean isMarketable;

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

	public boolean isMarketable() {
		return isMarketable;
	}

	public void setMarketable(boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
    
    
    
}
