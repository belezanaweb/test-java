package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INVENTORY", schema= "BLZ_PRODUCTS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "inventory_id")
public final class Inventory implements Serializable {

  
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventory_id;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false) 
    private Product product;
 
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL) 
    private List<Warehouse> warehouses;
    
    public void addWarehouses(Warehouse warehouse){
        if(warehouses == null){
        	warehouses = new ArrayList<>();
        }
        warehouse.setInventory(this);
        warehouses.add(warehouse);
    }
}
