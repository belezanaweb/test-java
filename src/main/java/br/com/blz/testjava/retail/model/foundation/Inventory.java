package br.com.blz.testjava.retail.model.foundation; 

import java.util.ArrayList;
import java.util.List;

public class Inventory {

     private List<Warehouse> warehouses = null;
    /*
   public Inventory (long quantity) {
    this.quantity = quantity;
  }*/

    public Inventory () {
      
      this.warehouses = new ArrayList<Warehouse> ();
    }

    public Inventory (String locality, long quantity) {
        if (this.warehouses == null) {
            this.warehouses = new ArrayList<Warehouse> ();
        } 
        this.warehouses.add(new Warehouse(locality, quantity, ""));        
      }
    
      //  A propriedade inventory.quantity é a soma da quantity dos warehouses
    public long getQuantity () {
      
      long quantity = 0;
        for (Warehouse w :  warehouses) 
        {
            quantity = quantity + w.getQuantity(); 
        } 
        return quantity;

    } 
  
   public List<Warehouse> getWarehouses() {
     return this.warehouses;
   }

} 