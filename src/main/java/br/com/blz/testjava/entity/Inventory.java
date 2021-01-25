package br.com.blz.testjava.entity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Inventory {

    private Integer quantity;
    private List<Warehouse> warehouses;

    public Inventory(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
    
    public Integer getQuantity() {
    	
    	this.quantity = 0;
    	
    	List< Warehouse > list =   this.getWarehouses();
		for (Warehouse wareHouse : list) {
			this.quantity += wareHouse.getQuantity();
		}
		
    	return this.quantity;
   }    
}