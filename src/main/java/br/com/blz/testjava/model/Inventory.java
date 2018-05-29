package br.com.blz.testjava.model;

import java.util.List;

public class Inventory {

    private Integer quantity;
    private List<WareHouse> wareHouses;

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        this.quantity = 0;
        for (WareHouse wareHouse : wareHouses) {
            this.quantity += wareHouse.getQuantity();
        }
        return this.quantity;
    }
    
    /**
     * @return the wareHouses
     */
    public List<WareHouse> getWareHouses() {
        return wareHouses;
    }

    /**
     * @param wareHouses the wareHouses to set
     */
    public void setWareHouses(List<WareHouse> wareHouses) {
        this.wareHouses = wareHouses;
    }

}