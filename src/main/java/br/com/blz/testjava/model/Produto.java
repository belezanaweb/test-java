/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.blz.testjava.model;

public class Produto {
 
  private Integer sku;
  private String name;
  private Inventory inventory = new Inventory();
  
  public Produto() {
      
  }
   
    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
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

    public Boolean getIsMarketable() {
        return  this.getInventory().getQuantity() > 0;
    }
   
}