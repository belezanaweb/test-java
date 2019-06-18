package br.com.blz.testjava.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Evandro Lopes da Rocha (evandro.esw@gmail.com)
 * @date 13/06/2019.
 */
@Entity
@Table(name="warehouses")
public class Warehouses implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 66835110598778057L;
	
	private long id;
	private String locality;
    private long quantity;
    private String type;
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "locality", nullable=true)
    public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	@Column(name = "quantity", nullable=true)
	public long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "type", nullable=true)
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
