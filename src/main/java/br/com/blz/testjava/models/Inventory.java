package br.com.blz.testjava.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_INVENTORY")
public class Inventory implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany
    private List<Warehouse> warehouses;
    private int quantity;

    public Inventory() {
    }

    public Inventory(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity() {
        int qtd = 0;
        for(Warehouse wh : warehouses){
            qtd += wh.getQuantity();
        }
        quantity = qtd;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public String toString() {
        return "Inventory{" + "id=" + id + ", quantity=" + quantity + ", warehouses=" + warehouses + '}';
    }
    
    
}
