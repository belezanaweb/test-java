package br.com.blz.testjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Warehouses {

    @Id
    @GeneratedValue
    @JsonIgnore
    @Column(name = "codigo")
    private long codigo;

    @Column(name = "locality")
    private String locality;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @ManyToOne
    private Inventory inventory;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
    
    
    

}
