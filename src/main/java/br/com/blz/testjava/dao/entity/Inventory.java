
package br.com.blz.testjava.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Inventory implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private Long id; 
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, targetEntity=Warehouse.class)    
    private List<Warehouse> warehouses;
    @Transient
    private Long quantity;
    private final static long serialVersionUID = -5067163988590103177L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ApiModelProperty(required = false, hidden = true)
    public Long getQuantity() {
		if(warehouses == null) {
			return this.quantity = 0L;
		}
		return this.quantity = warehouses.stream().mapToLong(p -> p.getQuantity()).sum();
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}