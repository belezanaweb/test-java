package br.com.blz.testjava.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Transient;

public class Inventory {
     
	 @Transient
	 private SkuProduct skuProduct;
     
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Transient
	 long id; 
	 @OneToMany
	 private Warehouse[] warehouses;

	    private long quantity;
         
	    public Warehouse[] getWarehouses ()
	    {
	        return warehouses;
	    }

	    public void setWarehouses (Warehouse[] warehouses)
	    {
	        this.warehouses = warehouses;
	    }

	    public long getQuantity ()
	    {
	        return quantity;
	    }

	    public void setQuantity (Long quantity)
	    {  
	        this.quantity = quantity;
	    }

	   
				
				      
				   
			
			  
	


	
	
}
