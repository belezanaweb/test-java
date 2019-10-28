
package br.com.blz.testjava.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Inventory implements Serializable {
	@Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    private Integer quantity;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, targetEntity=Warehouse.class, fetch=FetchType.EAGER)    
    private List<Warehouse> warehouses;
    private final static long serialVersionUID = -5067163988590103177L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Inventory() {
    }

    /**
     * 
     * @param quantity
     * @param warehouses
     */
    public Inventory(Integer quantity, List<Warehouse> warehouses) {
        super();
        this.quantity = quantity;
        this.warehouses = warehouses;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Inventory withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Inventory withWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("quantity", quantity).append("warehouses", warehouses).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(warehouses).append(id).append(quantity).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Inventory) == false) {
            return false;
        }
        Inventory rhs = ((Inventory) other);
        return new EqualsBuilder().append(warehouses, rhs.warehouses).append(id, rhs.id).append(quantity, rhs.quantity).isEquals();
    }

}
