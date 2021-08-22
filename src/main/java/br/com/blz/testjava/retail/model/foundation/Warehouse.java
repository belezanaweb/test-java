package br.com.blz.testjava.retail.model.foundation; 


public class Warehouse {
  
  
    private String locality; 
    private long quantity; 
    private String type; 

   public Warehouse (String locality, long quantity, String type) 
   {
    this.locality = locality;
    this.quantity = quantity;
    this.type = type;
   }

   public String getLocality () {
    return this.locality ;

   }
    public void setLocality (String locality) {
     this.locality = locality;

    }


    public void setQuantity (long quantity) {
      this.quantity = quantity;
     }
 
    public long getQuantity () {
      return this.quantity;

      }




    public void setType (String type) {
      this.type = type;
     }
 
    public String getType () {
      return this.type;

      }
 
} 