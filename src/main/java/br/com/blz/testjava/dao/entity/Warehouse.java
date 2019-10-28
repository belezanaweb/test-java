
package br.com.blz.testjava.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Warehouse implements Serializable {
	@Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    private String locality;
    private Integer quantity;
    private String type;
    private final static long serialVersionUID = -4778050054619097602L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Warehouse() {
    }

    /**
     * 
     * @param quantity
     * @param locality
     * @param type
     */
    public Warehouse(String locality, Integer quantity, String type) {
        super();
        this.locality = locality;
        this.quantity = quantity;
        this.type = type;
    }
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Warehouse withLocality(String locality) {
        this.locality = locality;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Warehouse withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Warehouse withType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("locality", locality).append("quantity", quantity).append("type", type).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(locality).append(quantity).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Warehouse) == false) {
            return false;
        }
        Warehouse rhs = ((Warehouse) other);
        return new EqualsBuilder().append(id, rhs.id).append(locality, rhs.locality).append(quantity, rhs.quantity).append(type, rhs.type).isEquals();
    }

}
