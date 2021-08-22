package br.com.blz.testjava.retail.model.foundation; 

public class Item {

    private String SKU; 
    private String Name; 
    

  //  private boolean isMarketable; 
    private Inventory inventory;
     
    public Item () {

        
    }

    public Item (String SKU) {
        // messing around to play when only SKU parameter is passed by
         this.SKU = SKU;
         this.Name = SKU + " - New Item";
         this.setInventory(new Inventory());
        
    }
    public Item (String SKU, String Name, String locality, long quantity) {
        this.SKU = SKU;
        this.Name = Name;
        this.setInventory(new Inventory(locality, quantity));
        
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getSKU() {
        return SKU; 
    }

    public String getName() {
        return Name; 
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    //  Um produto é marketable sempre que seu inventory.quantity for maior que 0
    public boolean isMarketable() {
       if ( this.inventory !=null) {
          if (this.inventory.getQuantity() > 0) {
                return true;
          }           
       }
       return false;

    } 

} 