package br.com.blz.testjava.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PRODUCT")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private long sku;
    @NotNull
    private String name;
    @OneToOne
    private Inventory inventory;
    private boolean isMarketable = false;

    public Product() {
    }

    public Product(long sku, String name, Inventory inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }
    
    public boolean marketable(){
        if(inventory.getQuantity() > 0){
            isMarketable = true;
            return true;
        }
        isMarketable = false;
        return false;
    }

    public long getSku() {
        return sku;
    }

    public void setSku(long sku) {
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

    public boolean isIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(boolean isMarketable) {
        this.isMarketable = isMarketable;
    }

    @Override
    public String toString() {
        return "Product{" + "sku=" + sku + ", name=" + name + ", inventory=" + inventory + ", isMarketable=" + isMarketable + '}';
    }
    
    
}

